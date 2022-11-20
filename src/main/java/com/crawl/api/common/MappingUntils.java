package com.crawl.api.common;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MappingUntils {
    static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static final DateTimeFormatter formatAlt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final Converter<String, LocalDateTime> strToDate = new Converter<String, LocalDateTime>() {
        @Override
        public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
            if(mappingContext.getSource() == null) return null;
            try{
                return LocalDateTime.parse(mappingContext.getSource(), format);
            }catch(DateTimeParseException ex){
                try{
                    return LocalDateTime.parse(mappingContext.getSource(), formatAlt);
                }catch (DateTimeParseException e){
                    return null;
                }
            }
        }
    };
}
