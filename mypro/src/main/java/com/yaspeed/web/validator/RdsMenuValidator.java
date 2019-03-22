package com.yaspeed.web.validator;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaspeed.web.service.RdsMenuService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hch
 * @ClassName: RdsMenuValidator
 * @Description:判断唯一性
 * @date 2018/3/12.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RdsMenuValidator.SysMenuValidatorImpl.class)
public @interface RdsMenuValidator {

    String value() default "";

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SysMenuValidatorImpl implements ConstraintValidator<RdsMenuValidator,String> {

        @Autowired
        private RdsMenuService rdsMenuService;

        @Override
        public void initialize(RdsMenuValidator rdsMenuValidator) {
        	
        }

        @Override
       public boolean isValid(String menuId, ConstraintValidatorContext constraintValidatorContext){
        	boolean result=false;
        	try {
        		result=rdsMenuService.getRdsMenuByMenuId(menuId)==null;
			} catch (Exception e) {
				e.printStackTrace();
			}
        	return result;
        }

    }
}

