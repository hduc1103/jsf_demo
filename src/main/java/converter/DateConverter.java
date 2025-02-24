package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter<Object> {

    private static final SimpleDateFormat DISPLAY_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    private static final SimpleDateFormat[] INPUT_FORMATTERS = {
        new SimpleDateFormat("dd/MM/yyyy"),  
        new SimpleDateFormat("yyyy-MM-dd"),  
        new SimpleDateFormat("yyyy/MM/dd"),  
        new SimpleDateFormat("dd-MM-yyyy"),  
        new SimpleDateFormat("MM/dd/yyyy")   
    };

    static {
        for (SimpleDateFormat formatter : INPUT_FORMATTERS) {
            formatter.setLenient(false);
        }
        DISPLAY_FORMATTER.setLenient(false);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        value = value.trim().replace("-", "/");

        for (SimpleDateFormat formatter : INPUT_FORMATTERS) {
            try {
                return formatter.parse(value); 
            } catch (ParseException e) {
            }
        }

        throw new ConverterException("Invalid date format. Use dd/MM/yyyy or yyyy-MM-dd.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        
        if (value instanceof Date) {
            return DISPLAY_FORMATTER.format((Date) value);
        }

        return "";
    }
}
