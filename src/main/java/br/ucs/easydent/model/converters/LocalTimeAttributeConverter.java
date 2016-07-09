package br.ucs.easydent.model.converters;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime locDate) {
		return (locDate == null ? null : Time.valueOf(locDate));
	}

	@Override
	public LocalTime convertToEntityAttribute(Time sqlDate) {
		return (sqlDate == null ? null : sqlDate.toLocalTime());
	}
}