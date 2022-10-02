package org.aibles.message.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Import({
    LanguageConfiguration.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface Message {
}
