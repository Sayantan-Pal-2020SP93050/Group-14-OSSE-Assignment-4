package com.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.model.RegistrationBean;

import com.constants.Constant;

@Component
public class CustomValidator implements Validator {

	public void validate(Object obj, Errors error) {

		RegistrationBean registorBean = (RegistrationBean) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(error, Constant.userName, Constant.errorUserName, Constant.userNameBlankValMsg);
		long phone = registorBean.getContactNumber();
		String str = String.valueOf(phone);
		if ((str.length() != 10)) {
			error.rejectValue(Constant.contactNo, Constant.errorContactNo,
					Constant.errorContactNoValMsg);
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(error, Constant.emailId, Constant.errorEmailValMsg, Constant.emailBlankValMsg);
		ValidationUtils.rejectIfEmptyOrWhitespace(error, Constant.confirmEmailId, Constant.errorConfirmEmailValMsg,
				Constant.confirmEmailNameBlankValMsg);
		if (!(registorBean.getConfirmEmailId().equals(registorBean.getEmailId()))) {
			error.rejectValue(Constant.emailId, Constant.errorEmailValMsg, Constant.emailFormatValMsg);
			error.rejectValue(Constant.confirmEmailId, Constant.confirmEmailId, Constant.emailFormatValMsg);
		}
		if (!(registorBean.isStatus())) {
			error.rejectValue(Constant.status, Constant.errorStat, Constant.termsAndCondMsg);
		}

	}

	public boolean supports(Class<?> arg0) {

		return false;
	}

}
