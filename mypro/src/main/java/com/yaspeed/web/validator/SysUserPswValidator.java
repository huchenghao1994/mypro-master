package com.yaspeed.web.validator;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.yaspeed.core.util.EncryptPasswd;
import com.yaspeed.web.pojo.ActiveUser;
import com.yaspeed.web.service.RdsRoleService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hch
 * @ClassName: SysUserValidator
 * @Description:判断唯一性
 * @date 2018/3/12.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SysUserPswValidator.SysUserPswValidatorImpl.class)
public @interface SysUserPswValidator {

    String value() default "";

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SysUserPswValidatorImpl implements ConstraintValidator<SysUserPswValidator,String> {
//        @Autowired
//        private SysUserService sysUserService;
//        
        @Override
        public void initialize(SysUserPswValidator sysUserPswValidator) {
        	
        }

        @Override
       public boolean isValid(String paswdOld, ConstraintValidatorContext constraintValidatorContext){
        	boolean result=false;
        	try {
        	//	result=sysUserService.pswIsBoolean(paswdOld);
        	} catch (Exception e) {
				e.printStackTrace();
			}
        	return result;
        }

    }
}

