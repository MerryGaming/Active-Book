package org.aibles.book.util;

import java.util.List;
import java.util.Locale;
import java.util.Locale.LanguageRange;
import javax.servlet.http.HttpServletRequest;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.aibles.book.constant.LanguageConstants;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Slf4j
public class HeaderLocalResolver extends AcceptHeaderLocaleResolver {

  @Override
  public Locale resolveLocale(HttpServletRequest request) {
    if (StringUtils.isBlank(request.getHeader("Accept-Language"))) {
      return Locale.getDefault();
    }
    List<LanguageRange> list = LanguageRange.parse(request.getHeader("Accept-Language"));
    log.info("Language Range : {}", list);
    Locale locale = Locale.lookup(list, LanguageConstants.LOCALES);
    return locale;
  }
}
