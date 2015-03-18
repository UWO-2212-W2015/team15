
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import team15.UserOjects.Location;
import team15.UserOjects.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vsippola
 */
public class script {
    public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException{
        BufferedReader input = new BufferedReader(new FileReader("citylist2.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("citylist3.txt"));
        
        ArrayList<Location> loc = new ArrayList();
        while(input.ready()){
            String[] s = (input.readLine()).split("\t");
            String gps = "";
            if(s.length==4)gps = s[3];
            Location l = new Location(s[0], s[1], s[2], gps);
            loc.add(l);
        }
        
        FileOutputStream fo = new FileOutputStream("locations.dat");
        ObjectOutputStream out = new ObjectOutputStream(fo);
        out.writeObject(loc);
        out.close();
        
        input.close();
        output.close();
    }
}
