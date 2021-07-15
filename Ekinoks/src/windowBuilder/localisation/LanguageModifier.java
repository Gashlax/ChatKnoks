package windowBuilder.localisation;
import java.util.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageModifier {
	final String langTur="tr";
	final String countryTur="TR";
	final Locale tr =new Locale(langTur,countryTur);
	final String filenameTur= "windowBuilder/localisation/Bundle_tr_TR";
	
	
	final String langEn="en";
	final String countryEn="US";
	final Locale eng =new Locale(langEn,countryEn);
	final String filenameEng= "windowBuilder/localisation/Bundle";
	
	
	

	public ResourceBundle swapLanguage(boolean isTurkish) {
		if(!isTurkish) {
			ResourceBundle rb=ResourceBundle.getBundle(filenameTur, tr);
			return rb;
		}else {
			ResourceBundle rb2=ResourceBundle.getBundle(filenameEng, eng);
			return rb2;
		}

	}
	
	
	public ResourceBundle swapLoginLanguage(boolean isTurkish) {
		if(!isTurkish) {
			ResourceBundle rb=ResourceBundle.getBundle(filenameTur, tr);
			return rb;
		}else {
			ResourceBundle rb2=ResourceBundle.getBundle(filenameEng, eng);
			return rb2;
		}

	}
}