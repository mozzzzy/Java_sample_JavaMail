/*
 * JavaMailのサンプル
 *	JavaMail のjarファイルの取得は、https://java.net/projects/javamail/pages/Homeから。
 * 	サンプルはhttp://www.javadrive.jp/javamail/smtp/index1.htmlから。
 */


import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/*mailを送るクラス*/
public class msgsendsample{

	/*メールで送るメッセージ*/
	static String msgText = "This is a test message. This is a first line.\nThis is a next line.";

	/*mainメソッド*/
	public static void main(String[] args){
		//もし引数の数が4つじゃなかったら、usageを出力して、プログラム終了
		if (args.length != 4){
			//使用法の出力
			usage();
			//プログラムの終了
			System.exit(1);
		}

		//空行を出力
		System.out.println();

		//変数にそれぞれの値を記憶
		String to = args[0];	//宛先
		String from = args[1];	//送信元
		String host = args[2];	//SMTPサーバのホスト名
		String port = "25";	//SMTPサーバのポート番号
		String starttls = "true";
		boolean debug = Boolean.valueOf(args[3]).booleanValue();	//debugモードが  True か False


		/*セッション情報の作成*/
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		if (debug){
			props.put("mail.debug", args[3]);
		}

		Session session = Session.getInstance(props, null);

		session.setDebug(debug);

		try{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = {new InternetAddress(args[0])};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Sample Subject");
			msg.setSentDate(new Date());
			msg.setText(msgText);

			Transport.send(msg);
		}catch(MessagingException mex){
			System.out.println("\n--Exception handling in msgsendsample.java");
			mex.printStackTrace();
		}
	}

	/*Usageの出力*/
	private static void usage(){
		System.out.println("usage: java msgsendsample <to> <from> <smtp> true|false");
	}
}


