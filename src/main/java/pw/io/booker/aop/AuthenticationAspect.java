package pw.io.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import pw.io.booker.repo.AuthenticationRepository;

@Aspect
@Component
public class AuthenticationAspect {

	private AuthenticationRepository authenticationRepository;

	public AuthenticationAspect(AuthenticationRepository authenticationRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(tokenID,..) && !execution(* pw.io.booker.controller.CustomerController.saveAll())")
	public Object checkAuthentication(ProceedingJoinPoint joinPoint, String tokenID) throws Throwable {

		if (tokenID == null) {

			throw new RuntimeException("Please input a token ID!");

		}

		if (authenticationRepository.findByToken(tokenID) == null) {

			throw new RuntimeException("Invalid credentials!");

		}

		return joinPoint.proceed();

	}

}
