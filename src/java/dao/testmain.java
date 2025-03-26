/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Product;
import model.User;
/**
 *
 * @author thang
 */
public class testmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductDao prd = new ProductDao();
        ColorDao cd = new ColorDao();
        ArrayList<Product> productList = prd.getAllProductDetail(1);
        
//        for(Product p : productList){
//            System.out.println(p.getImage());
//            System.out.println(p.getProductName());
//            System.out.println(p.getMaxPrice());
//            System.out.println(p.getMinPrice());
//            System.out.println(p.getProducer());
//            System.out.println(p.getProductType());
//            
//        }
//        LoginDao lgd = new LoginDao();
//        User u = lgd.findUserbyId(1);
//        System.out.println(u.getAddress());
//        System.out.println(u.getEmail());
//        System.out.println(u.getMobileNumber());
//        System.out.println(u.getRole());
//        System.out.println(u.getStatus());
//        System.out.println(u.getUserID());
//        System.out.println(u.getUserName());
//         List<Color> a= cd.getActiveColor("1");
//         for(Color c : a){
//             System.out.println(c.getColorID());
//             System.out.println(c.getColor());
//             System.out.println(c.getStatus());
//         }

            List<Color> c = cd.getAllColor("1");
            for(Color cc: c){
                System.out.println(cc.getColorID());
                System.out.println(cc.getColor());
                System.out.println(cc.getStatus());
            }
    
    }
    
    public static int checkEmailExist(String email) {
        LoginDao logindao = new LoginDao();
        ArrayList<User> mail = logindao.getUserEmail();
        String currentmail;
        for (int i = 0; i < mail.size(); i++) {
            currentmail = mail.get(i).getEmail();
            if (email.equals(currentmail)) {    //return true if found email in db
                return mail.get(i).getUserID();
            }
        }
        return -1;     //return false if not found
    }
    
}
