package com.sid.moviebkg.common.utils;

import com.sid.moviebkg.common.logging.MBkgLogger;
import com.sid.moviebkg.common.logging.MBkgLoggerFactory;
import org.springframework.util.StringUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    private static MBkgLogger logger = MBkgLoggerFactory.getLogger(DateTimeUtil.class);

    private static final String formatterPattern = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtil() {}

    public static String convertToUtcTime(String dateStr, String offset) {
        return convertToUtcTime(dateStr, offset, formatterPattern);
    }

    public static String convertToUtcTime(String dateStr, String offset, String pattern) {
        String dateTimeInUtc = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime localDateTime = getTimeInUtc(dateStr, offset, pattern);
            if (localDateTime != null) {
                // Formatting to make sure 00 seconds are not getting dropped
                dateTimeInUtc = localDateTime.format(formatter);
                // Formatting to make sure date time is in format suitable for XMLGeorgian Calendar
                dateTimeInUtc = formatDateTime(dateTimeInUtc);
            }
        } catch (Exception e) {
            logger.error("convertToUtcTime failed for date: {}, with exception ", dateStr, e);
            dateTimeInUtc = dateStr;
        }
        return dateTimeInUtc;
    }

    public static LocalDateTime getTimeInUtc(String dateStr, String offset, String pattern) {
        LocalDateTime localDateTime = null;
        if (StringUtils.hasText(dateStr) && StringUtils.hasText(offset)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (!isDateFormatInvalid(dateStr)) {
                    localDateTime = LocalDateTime.parse(dateStr, formatter);
                    ZoneOffset zoneOffset = ZoneOffset.of(offset);
                    ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneOffset);
                    localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))
                            .toLocalDateTime().truncatedTo(ChronoUnit.MILLIS);
                }
            } catch (Exception e) {
                logger.error("getTimeInUtc failed for date: {}, with exception ", dateStr, e);
            }
        }
        return localDateTime;
    }

    public static String formatDateTime(String dateStr) {
        String formattedDate = dateStr;
        if (StringUtils.hasText(dateStr)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterPattern);
                if (!isDateFormatInvalid(dateStr)) {
                    // Dtm value is of type XmlGregorianCalendar. XmlGregorianCalendar recognizes datetime which is in format yyyy-MM-dd'T'HH:mm:ss
                    String gregCalendarFormat = "yyyy-MM-dd'T'HH:mm:ss";
                    DateTimeFormatter gregCalendarFormatter = DateTimeFormatter.ofPattern(gregCalendarFormat);
                    // parse method will drop the trailing seconds if it is 00. Need to format again with formatter to retain seconds
                    formattedDate = LocalDateTime.parse(dateStr, formatter).format(gregCalendarFormatter);
                }
            } catch (Exception e) {
                logger.error("formatDateTime failed for date: {}, with exception ", dateStr, e);
            }
        }
        return formattedDate;
    }

    public static boolean isDateFormatInvalid(String dateStr) {
        boolean isInvalid = formatterPattern.length() != dateStr.length();
        if (isInvalid) {
            logger.info("Incoming date: {}, is not in format: {}", dateStr, formatterPattern);
        }
        return isInvalid;
    }

    public static LocalDate gregorianCalendarToLocalDate(XMLGregorianCalendar dt) {
        LocalDate localDate = null;
        if (dt != null && dt.toGregorianCalendar() != null &&
        dt.toGregorianCalendar().toZonedDateTime() != null && dt.toGregorianCalendar().toZonedDateTime().toLocalDateTime() != null) {
            localDate = dt.toGregorianCalendar().toZonedDateTime().toLocalDate();
        }
        return localDate;
    }

    public static LocalDateTime gregorianCalendarToLocalDateTime(XMLGregorianCalendar dt) {
        LocalDateTime localDateTime = null;
        if (dt != null && dt.toGregorianCalendar() != null &&
                dt.toGregorianCalendar().toZonedDateTime() != null && dt.toGregorianCalendar().toZonedDateTime().toLocalDateTime() != null) {
            localDateTime = dt.toGregorianCalendar().toZonedDateTime().toLocalDateTime().truncatedTo(ChronoUnit.MILLIS);
        }
        return localDateTime;
    }

    public static LocalTime gregorianCalendarToLocalTime(XMLGregorianCalendar dt) {
        LocalTime localTime = null;
        if (dt != null && dt.toGregorianCalendar() != null &&
                dt.toGregorianCalendar().toZonedDateTime() != null && dt.toGregorianCalendar().toZonedDateTime().toLocalDateTime() != null) {
            String lclTime = dt.toGregorianCalendar().toZonedDateTime().toLocalDateTime().truncatedTo(ChronoUnit.MILLIS).toLocalTime().format(DateTimeFormatter.ISO_DATE_TIME);
            localTime = LocalTime.parse(lclTime);
        }
        return localTime;
    }

    public static String getCurrentDateInUtc() {
        return LocalDate.now(Clock.systemUTC()).toString();
    }

    public static LocalDateTime convertLocalTimeToUtc(LocalDateTime localDateTime, String offset) {
        LocalDateTime dateTimeInUtc = localDateTime;
        if (localDateTime != null && StringUtils.hasText(offset)) {
            try {
                ZoneOffset zoneOffset = ZoneOffset.of(offset);
                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneOffset);
                dateTimeInUtc = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"))
                        .toLocalDateTime().truncatedTo(ChronoUnit.MILLIS);
            } catch (Exception e) {
                logger.error("convertLocalTimeToUtc failed for date: {}, with exception ", localDateTime, e);
            }
        }
        return dateTimeInUtc;
    }

    public static String getTime(String eventDateTime) {
        return StringUtils.hasText(eventDateTime) ? LocalDateTime.parse(eventDateTime).toLocalDate().format(DateTimeFormatter.ofPattern("HHmmss")) : eventDateTime;
    }

    public static String getExecutionDate(String eventDateTime) {
        return StringUtils.hasText(eventDateTime) ? LocalDateTime.parse(eventDateTime).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : eventDateTime;
    }
}
