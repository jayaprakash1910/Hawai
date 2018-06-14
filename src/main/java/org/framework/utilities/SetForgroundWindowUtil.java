package org.framework.utilities;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public class SetForgroundWindowUtil {
	public interface User32 extends StdCallLibrary {
	      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

	      interface WNDENUMPROC extends StdCallCallback {
	         boolean callback(Pointer hWnd, Pointer arg);
	      }

	      boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer arg);

	      int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);

	      int SetForegroundWindow(Pointer hWnd);

	      Pointer GetForegroundWindow();
	   }

	   public static boolean setForegroundWindowByName(final String windowName,
	         final boolean starting) {
	      final User32 user32 = User32.INSTANCE;
	      return user32.EnumWindows(new User32.WNDENUMPROC() {

	         @Override
	         public boolean callback(Pointer hWnd, Pointer arg) {
	            byte[] windowText = new byte[512];
	            user32.GetWindowTextA(hWnd, windowText, 512);
	            String wText = Native.toString(windowText);
	            // if (wText.contains(WINDOW_TEXT_TO_FIND)) {
	            if (starting) {
	               if (wText.contains(windowName)) {
	                  user32.SetForegroundWindow(hWnd);
	                  return false;
	               }
	            } else {
	               if (wText.contains(windowName)) {
	                  user32.SetForegroundWindow(hWnd);
	                  return false;
	               }
	            }
	            return true;
	         }
	      }, null);
	   }

	   @SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
		   Process child =  Runtime.getRuntime().exec("C:\\Users\\WiFi Automation\\Desktop\\ChangeMACAddressBatch.bat");
	      boolean result = setForegroundWindowByName("ChangeMACAddress", true);
	      enterOptions();
	      ctrl_a();
	      System.out.println("result: " + result);
	      
	   }
	   
	   public static void alt_tab() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(2000);
		}
		
		public static void enterOptions() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_Q);
			robot.keyRelease(KeyEvent.VK_Q);
		}
		
		public static void ctrl_a() throws Exception {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(2000);
		}
	}
