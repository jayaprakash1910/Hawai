package org.framework.utilities;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.framework.Base;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class StringUtilsHelper extends Base {

	public static String getRandomPublicURL() {
			ArrayList<String> websites = new ArrayList<String>();
			
			try {
			//	websites.add("http://www.yahoo.com");
			//	websites.add("http://www.live.com");
			//	websites.add("http://www.msn.com");
				websites.add("http://www.ebay.com");
			//	websites.add("http://www.bing.com");
			//	websites.add("http://www.wordpress.com");
			//	websites.add("http://www.microsoft.com");
			//	websites.add("http://www.blogspot.com");
			//	websites.add("http://www.apple.com");
			//	websites.add("http://www.github.com");
				websites.add("http://www.adobe.com");
			//	websites.add("http://www.wikipedia.com");
				websites.add("http://www.bbc.com");
				websites.add("http://www.cnbc.com");
			//	websites.add("http://www.netflix.com");
				websites.add("http://www.cnn.com");
			//	websites.add("http://www.hbo.com");
			//	websites.add("http://www.pinterest.com");
			//	websites.add("http://www.tumblr.com");
				
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return websites.get(getRandomNumber(websites));
	}

	private static int getRandomNumber(ArrayList<String> listName) {
		Random rand = new Random();
		return rand.nextInt(listName.size());
	}

	public static String getUserCredentials_QA() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("TAC103277:TACEmailSys123");
		return credentials.get(getRandomNumber(credentials));
	}

	public static String getUserCredentials_StageProd() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("wifibig03:xfinity1");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getBelowTierResidentUserCredentials_QA() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("wifitestqa53:Tester123$");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getBelowTierBusinessUserCredentials_QA() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("qatest125@malisettycorp.comcastbiz.net:Comcast1");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getBelowTierUserResidentialCredentials_Prod() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("UpdatetoGregD@Daoonstreem.comcastbiz.net:Comcast1");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getBelowTierUserCredentials_QA() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("wifitestqa53:Tester123$");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getAboveTierBusinessUserCredentials_QA() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("sri@wifitest.comcastbiz.net:Comcast1");
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getAboveTierBusinessUserCredentials_StageProd() {
		ArrayList<String> credentials = new ArrayList<String>();
		credentials.add("NewStroller2@GracooBabee.comcastbiz.net:Comcast1");
		return credentials.get(getRandomNumber(credentials));
	}

	public static String getUsername_QA() {
		String[] username = getUserCredentials_QA().split(":");
		return username[0];
	}

	public static String getPassword_QA() {
		String[] password = getUserCredentials_QA().split(":");
		return password[1];
	}
	
	public static String getBelowTierResidentUsername_QA() {
		String[] username = getBelowTierResidentUserCredentials_QA().split(":");
		return username[0];
	}
	
	public static String getBelowTierResidentPassword_QA() {
		String[] password = getBelowTierResidentUserCredentials_QA().split(":");
		return password[1];
	}
	
	public static String getBelowTierBusinessUsername_QA() {
		String[] username = getBelowTierBusinessUserCredentials_QA().split(":");
		return username[0];
	}
	
	public static String getBelowTierBusinessPassword_QA() {
		String[] password = getBelowTierBusinessUserCredentials_QA().split(":");
		return password[1];
	}
	
	public static String getAboveTierBusinessUsername_QA() {
		String[] username = getAboveTierBusinessUserCredentials_QA().split(":");
		userId = username[0];
		return username[0];
	}
	
	public static String getAboveTierBusinessPassword_QA() {
		String[] password = getAboveTierBusinessUserCredentials_QA().split(":");
		return password[1];
	}

	public static String getBelowTierUsername_StageProd() {
		String[] username = getBelowTierUserResidentialCredentials_Prod().split(":");
		return username[0];
	}
	
	public static String getBelowTierPassword_StageProd() {
		String[] password = getBelowTierUserResidentialCredentials_Prod().split(":");
		return password[1];
	}
	
	public static String getAboveTierBusinessUsername_StageProd() {
		String[] username = getAboveTierBusinessUserCredentials_StageProd().split(":");
		userId = username[0];
		return username[0];
	}
	
	public static String getAboveTierBusinessPassword_StageProd() {
		String[] password = getAboveTierBusinessUserCredentials_StageProd().split(":");
		return password[1];
	}
	
	public static String getUsername_StageProd() {
		String[] username = getUserCredentials_StageProd().split(":");
		userId = username[0];
		return username[0];
	}

	public static String getPassword_StageProd() {
		String[] password = getUserCredentials_StageProd().split(":");
		return password[1];
	}
	
	public static String getNonExistingUserCredentials() {
		ArrayList<String> credentials = new ArrayList<String>();
		
		try {
			credentials.add("test@gmail.com:19103");
			credentials.add("wodnm01@gmail.com:19103");
			credentials.add("wodnm002@gmail.com:19103");
		} catch(Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return credentials.get(getRandomNumber(credentials));
	}
	
	public static String getNonExistingUser_Email() {
		String[] email = getNonExistingUserCredentials().split(":");
		return email[0];
	}
	
	public static String getNonExistingUser_Zipcode() {
		String[] zipcode = getNonExistingUserCredentials().split(":");
		return zipcode[1];
	}
	
	public static String getRandomNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		try {
			names.add("Samual:Pai");
			names.add("Jonathan:Sweeney");
			names.add("Donovan:Tafolla");
			names.add("Victor:Melillo");
			names.add("Shaina:Motta");
			names.add("Margareta:Bennington");
			names.add("Roselia:Horan");
			names.add("Lilly:Heal");
			names.add("Patience:Gratz");
			names.add("Enid:Lover");
			names.add("Tamera:Knorr");
			names.add("Stephany:Arizmendi");
			names.add("Maximina:Centers");
			names.add("Edyth:Eggleston");
			names.add("Bong:Rossbach");
			names.add("Jenice:Cookingham");
			names.add("Mikaela:Mcgahee");
			names.add("Lore:Filkins");
			names.add("Brandy:Barish");
			names.add("Coleen:Martello");
			names.add("Coretta:Clay");
			names.add("Alise:Madison");
			names.add("Stepanie:Erskine");
			names.add("Celinda:Sharpless");
			names.add("Jill:Stiefel");
			names.add("Edythe:Luman");
			names.add("Marjory:Buda");
			names.add("Valery:Lindemann");
			names.add("Suk:Beckwith");
			names.add("Nona:Holdman");
			names.add("Amber:Guth");
			names.add("Vita:Block");
			names.add("Yoshie:Truelove");
			names.add("Buddy:Souza");
			names.add("Mariko:Slack");
			names.add("Claudia:Houseman");
			names.add("Versie:Fuselier");
			names.add("Will:Hendershott");
			names.add("Donn:Carrion");
			names.add("Carmela:Twyman");
			names.add("Isabelle:Stull");
			names.add("Rosella:Garbarino");
			names.add("Renetta:Wolpert");
			names.add("Bunny:Blass");
			names.add("Mirta:Kohan");
			names.add("Cindi:Surrett");
			names.add("Onita:Salmons");
			names.add("Hermine:Bilski");
			names.add("Jetta:Eastwood");
			names.add("Carlena:Cosme");
			names.add("Delta:Villegas");
			names.add("Cathey:Hovland");
			names.add("Ngoc:Willman");
			names.add("Jaleesa:Stackhouse");
			names.add("Loretta:Macintyre");
			names.add("Annice:Quandt");
			names.add("Angie:Dupree");
			names.add("Tamatha:Dilley");
			names.add("Shirleen:Esposito");
			names.add("Leia:Ciotti");
			names.add("Manuela:Schmitmeyer");
			names.add("Amberly:Parkinson");
			names.add("Gabriela:Shur");
			names.add("Kori:Bigler");
			names.add("Lurlene:Quesada");
			names.add("Consuelo:Camel");
			names.add("Hermine:Ammerman");
			names.add("Wanda:Zeolla");
			names.add("Hisako:Pacifico");
			names.add("Sharmaine:Grinnell");
			names.add("Jefferey:Eisenman");
			names.add("Gary:Scovil");
			names.add("Thad:Zacharias");
			names.add("Maricela:Giles");
			names.add("Sean:Studebaker");
			names.add("Randell:Condit");
			names.add("Jodee:Portugal");
			names.add("Williams:Cheslock");
			names.add("Lexie:Fortson");
			names.add("Rosendo:Berkley");
			names.add("Norman:Almaguer");
			names.add("Melonie:Pless");
			names.add("Caterina:Fergus");
			names.add("Madison:Rowser");
			names.add("Randy:Fabre");
			names.add("William:Welborn");
			names.add("Barbera:Pegg");
			names.add("Khadijah:Fells");
			names.add("Marybelle:Hecht");
			names.add("Gordon:Kohan");
			names.add("Karole:Sober");
			names.add("Denyse:Schuelke");
			names.add("Michelina:Dingus");
			names.add("Katina:Auten");
			names.add("Shanti:Shippy");
			names.add("Maryland:Ku");
			names.add("Yessenia:Sumrall");
			names.add("Marissa:Banning");
			names.add("Rosalba:Forbes");
			names.add("Darron:Soliman");
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return names.get(getRandomNumber(names));
	}
	
	public static String getFirstName() {
		String[] firstName = getRandomNames().split(":");
		return firstName[0];
	}
	
	public static String getLastName() {
		String[] lastName = getRandomNames().split(":");
		return lastName[1];
	}
	
	public static String getRandomEmailAddress() {
		return "wodnm" + generateRandomNumeric(8) + "@gmail.com";
	}
	
	public static String getRandomZipCodes() {
		ArrayList<String> zipcode = new ArrayList<String>();
		
		try {
			zipcode.add("19126");
			zipcode.add("19125");
			zipcode.add("19128");
			zipcode.add("19127");
			zipcode.add("19130");
			zipcode.add("19129");
			zipcode.add("19132");
			zipcode.add("19131");
			zipcode.add("19134");
			zipcode.add("19133");
			zipcode.add("19136");
			zipcode.add("19135");
			zipcode.add("19138");
			zipcode.add("19137");
			zipcode.add("19140");
			zipcode.add("19139");
			zipcode.add("19142");
			zipcode.add("19141");
			zipcode.add("19144");
			zipcode.add("19143");
			zipcode.add("19146");
			zipcode.add("19145");
			zipcode.add("19148");
			zipcode.add("19147");
			zipcode.add("19150");
			zipcode.add("19149");
			zipcode.add("19152");
			zipcode.add("19151");
			zipcode.add("19154");
			zipcode.add("19153");
			zipcode.add("19155");
			zipcode.add("19170");
			zipcode.add("19173");
			zipcode.add("19019");
			zipcode.add("19176");
			zipcode.add("19187");
			zipcode.add("19192");
			zipcode.add("19101");
			zipcode.add("19099");
			zipcode.add("19103");
			zipcode.add("19102");
			zipcode.add("19105");
			zipcode.add("19104");
			zipcode.add("19107");
			zipcode.add("19106");
			zipcode.add("19109");
			zipcode.add("19111");
			zipcode.add("19112");
			zipcode.add("19115");
			zipcode.add("19114");
			zipcode.add("19118");
			zipcode.add("19116");
			zipcode.add("19120");
			zipcode.add("19119");
			zipcode.add("19122");
			zipcode.add("19121");
			zipcode.add("19124");
			zipcode.add("19123");
		
		} catch (Exception e) {
			e.printStackTrace();
			Log4j.info(e.getMessage());
		}
		return zipcode.get(getRandomNumber(zipcode));
	}
	
	public static String generateRandomStrAlphabetic(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
	
	public static String generateRandomNumeric(int length) {
		return RandomStringUtils.randomNumeric(length);
	}
	
	public static String generateRandomStrAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public static int generateRandomInt(int maxNumber) {
		return new Random().nextInt(maxNumber);
	}
	
	public static int generateRandomIntInRange(int lowerBound, int upperBound) {
		return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
	}
	
	public static String generateRandomEmail() {
		return ("wodnm" + generateRandomNumeric(generateRandomIntInRange(1, 10)) + "@malinator.com");
	}
	
	public static void getCurrentTestMethodName(ITestResult result) {
		Base.currentTestMethodName = result.getMethod().getMethodName();
	}
	
	public static String getFirstNCharacters(String str, int n) {
		return str.substring(0, Math.min(str.length(), n));
	}
	
	public static String removeLastCharacter(String str) {
		if(str != null && str.length() > 0 && Character.isLetter(str.charAt(str.length()-1)) ) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public ArrayList<String> getGroupNamesFromTestMethods(Method method) {
		ArrayList<String> groupName = new ArrayList<String>();
		Test testClass = method.getAnnotation(Test.class);
		
		for (int i = 0; i < testClass.groups().length; i++) {
			System.out.println(testClass.groups()[i]);
			groupName.add(testClass.groups()[i]);
		}
		
		return groupName;
	}
	
	public static String generateRandomUUID() {
		UUID uuid = UUID.randomUUID();
	    return uuid.toString();
	}
	
	public static String stringToHexadecimal(String text) throws UnsupportedEncodingException {
	    byte[] myBytes = text.getBytes("UTF-8");

	    return DatatypeConverter.printHexBinary(myBytes);
	}
	
	public static String getTextWithOutDecimals(String sampleText) {
		if (sampleText.contains(".0")) {
			sampleText = sampleText.substring(0, (sampleText.length() - 2));
		} else if (sampleText.contains(".00")) {
			sampleText = sampleText.substring(0, (sampleText.length() - 3));
		}
		return sampleText;
	}
	
	public static String replaceCharacterInAString(String str, String oldStr, String newStr) {
		return str.replace(oldStr, newStr);
	}
}
