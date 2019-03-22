package com.yaspeed.web.validator;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

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
@Constraint(validatedBy=RdsRoleValidator.SysRoleValidatorImpl.class)
public @interface RdsRoleValidator {

    String value() default "";

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SysRoleValidatorImpl implements ConstraintValidator<RdsRoleValidator,String> {

        @Autowired
        private RdsRoleService rdsRoleService;

        @Override
        public void initialize(RdsRoleValidator rdsRoleValidator) {
        	
        }

        @Override
       public boolean isValid(String roleId, ConstraintValidatorContext constraintValidatorContext){
        	boolean result=false;
        	try {
        		result=rdsRoleService.getRdsRoleByRoleId(roleId)==null;
			} catch (Exception e) {
				e.printStackTrace();
			}
        	return result;
        }

    }
}

