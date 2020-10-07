package com.backend.digitalbank.view.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.digitalbank.infrastructure.IPersonalDataRepository;
import com.backend.digitalbank.infrastructure.IUserRepository;
import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.infrastructure.security.AuthenticationUtils;
import com.backend.digitalbank.model.PersonalData;
import com.backend.digitalbank.model.User;
import com.backend.digitalbank.view.service.policy.CanCreatePasswordPolicy;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private IEmailService emailService;

	@Autowired
	private IProposalService proposalService;

	@Autowired
	private IPersonalDataRepository peronalDataRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private CanCreatePasswordPolicy canCreatePasswordPolicy;

	@Autowired
	private AuthenticationUtils authenticationUtils;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	@Value("${token.expiration.minutes}")
	private Long minutesToExpiration;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public void createToken(String mail, String cpf){
		Optional<PersonalData> opPersonalData = peronalDataRepository.findByEmailDocument(mail, cpf);

		PersonalData personalData = opPersonalData.orElseThrow(() -> new EntityNotFoundException("Dados não encontrados."));
		proposalService.isProposalReleased(personalData.getProposal());

		String token = genetrateToken(6);

		User user = new User();
		user.setToken(token);
		user.setExpirationDate(LocalDateTime.now().plusMinutes(minutesToExpiration));
		user.setPersonalData(personalData);
		userRepository.save(user);

		emailService.sendEmail(personalData.getMail(), "Digital Bank - Token",
				"Olá " + personalData.getName() + " seu token é: " + token);
	}

	private String genetrateToken(int size) {
		String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
				"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		StringBuilder password = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int posicao = (int) (Math.random() * caracteres.length);
			password.append(caracteres[posicao]);
		}
		return password.toString();
	}

	@Override
	public void createPassword(String password, String token) {
		User user = userRepository.findByToken(token);

		canCreatePasswordPolicy.verifyToken(user);
		
		user.setPassword(this.encoder.encode(password));
		userRepository.save(user);
		
		emailService.sendEmail(user.getPersonalData().getMail(), "Senha alterada", 
				"Sua senha foi alterada em " + LocalDateTime.now().format(formatter));
	}

	@Override
	public String authenticate(String email, String password) {

		User user = userRepository.findByEmailPassword(email);

		if(null == user || !this.encoder.matches(password, user.getPassword())){
			throw new DigitalBankingException("Email/Senha inválido");
		}
		return authenticationUtils.getJWTToken(email);
	}
}
