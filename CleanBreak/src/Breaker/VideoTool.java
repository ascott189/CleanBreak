/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Breaker;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author a-scott-1
 */
public class VideoTool {
   BreakView BV = new BreakView();
   public static Logger logger = Logger.getLogger("BatchBraker");


   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      // TODO code application logic here
      BreakView.main();
   }
   
   public String GetStringByOS(String OS, String[] ToGet) {
      int OSX = 0, MS = 1;
      if (OS.toLowerCase().indexOf("mac") >= 0) {
         return ToGet[OSX];
      }
      else if (OS.toLowerCase().indexOf("win") >= 0) {
         return ToGet[MS];
      }
      return null;
   }
   
   public void ExectuteConvert() {
      if (BreakView.ToConvert == null) {
         BV.SetWarningText("You haven't selected any files to convert.");
         return;
      }
      else if (BreakView.Converted == null) {
         BV.SetWarningText("You haven't selected a destination location for the files.");
         return;
      }
      
      if (BreakView.ToConvert.isDirectory()) {
         File[] ConvertFiles = BreakView.ToConvert.listFiles();
         for (int i = 0; i < ConvertFiles.length; i++) {
            Convert(ConvertFiles[i]);
         }
      }
      else {
         Convert(BreakView.ToConvert);
      }
   }
   
   private String CleanName(String nameStr) {
      //char[] junkChars = BreakView.txtboxJunk.getText().toCharArray();
      String newName = nameStr;
      for (String replaceStr : BreakView.txtboxJunk.getText().split(",")) {
         System.out.println(newName.replaceAll(replaceStr, " "));
      }
      return newName;
   }
   
   private void Convert(File convertFile){
      int OSX = 0, MS = 1;
      String[] HBLoc = {"./Resources/HandBrakeCLI", ".\\Resources\\HandBrakeCLI.exe"};
      String OS = System.getProperty("os.name");
            
      try {
         Runtime RT = Runtime.getRuntime();

         // Gets default path 
         String Execute = GetStringByOS(OS, HBLoc);

         Execute += " -i " + convertFile.getPath();
         String newFileName = convertFile.getName();
         if (BreakView.cleanFileName) {
            newFileName = CleanName(newFileName);
         }
                 
         Execute += " -o " + BreakView.Converted + "/" + newFileName;

         if (BreakView.Preset != null)
            Execute += " --preset=\"" + BreakView.Preset + "\"";

         BV.SetWarningText(Execute);

         Process PC = RT.exec("ls");
         PC.waitFor();
         BufferedReader reader = 
             new BufferedReader(new InputStreamReader(PC.getInputStream()));

         String line = "";			
         while ((line = reader.readLine())!= null) {
                 System.out.println(line);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
