package com.example.recyclerviewrestaurantmenu;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodData {
    private static String [] foodName = {
            "American All Star",
            "American Classic",
            "American Frankie Sausage",
            "Cheese Mania",
            "Chicken Dominator",
            "Chicken Lover",
            "Extra Vaganzza",
            "Grilled Beef Supreme",
            "Meat Ball Blast",
            "Meatzza",
            "Mexican Beef Nachos",
            "Mexican Chicken Nachos",
            "Alfredo Beef Mushroom",
            "Alfredo Chicken Mushroom",
            "Ultimate Cheese Melt New",
            "veggie Mania"

    };

    private static String [] foodDetail = {
            "Tomato Sauce, Mustard Sauce, Jalapeno, Beef Rasher, Beef Crumble",
            "Tomato Sauce, Mustard Sauce, Tomato, Beef Rasher, Beef Crumble",
            "Rustic Sauce, Mustard, Mozzarella Cheese, Meaty Frankfurter Beef",
            "Domino's Pizza Sauce, Parsley, Extra Mozzarella Cheese",
            "Rustic Sauce, Green Paprika, Corn, Chili Chicken, Chicken Pepperoni",
            "Tomato Sauce, Chicken Rasher, parsley, Chicken Pepperoni, Marinated Chicken, Cheesy Bites, Mozzarella Cheese",
            "Domino's Pizza Sauce, Green Pepper, Onion, Black Olives, Mushroom, Beef",
            "BBQ Sauce, Cheese Sauce, Onion, Beef Burger, Jalapeno, Mozzarella",
            "BBQ Sauce, Liquid Cheese Sauce, Onion, Meatball, Parsley",
            "Domino's Pizza Sauce, Beef Pepperoni, Beef Sausage Cut, Beef",
            "Saus Domino's Pizza, Keju Mozzarella, Saus Keju Cheddar",
            "Saus Domino's Pizza, Keju Mozzarella, Saus Keju Cheddar",
            "Truffle Alfredo Sauce, Keju Mozzarella, Beef Burger, Onion",
            "Truffle Alfredo Sauce, Keju Mozzarella, Chili Chicken, Onion",
            "Liquid Cheese Sauce,Keju Mozzarella, Cheddar Cheese Sauce",
            "Domino's Pizza Sauce, Green Pepper, Corn, Mushroom, Tomato, Black Papper"
    };
    private static String [] foodPrice = {
            "Rp. 120,000",
            "Rp. 100,000",
            "Rp. 99,000",
            "Rp. 135,000",
            "Rp. 200,000",
            "Rp. 78,000",
            "Rp. 99,000",
            "Rp. 110,000",
            "Rp. 100,000",
            "Rp. 120,000",
            "Rp. 135,000",
            "Rp. 99,000",
            "Rp. 115,000",
            "Rp. 130,000",
            "Rp. 145,000",
            "Rp. 150,000"

    };
    private static int[] foodImage = {
            R.drawable.americanallstar,
            R.drawable.americanclassic,
            R.drawable.americanfrankiesausage,
            R.drawable.cheesemania,
            R.drawable.chickendominator,
            R.drawable.chickenlover,
            R.drawable.extravaganzza,
            R.drawable.grilledbeefsupreme,
            R.drawable.meatballblast,
            R.drawable.meatzza,
            R.drawable.mexicanbeefnachos,
            R.drawable.mexicanchickennachos,
            R.drawable.newyorkeralfredobeefmushroomtrufflebig,
            R.drawable.newyorkeralfredochickenmushroomtrufflebig,
            R.drawable.ultimatecheesemeltnew,
            R.drawable.veggiemania

    };

    static ArrayList<Food> getListData(){
        ArrayList<Food> list = new ArrayList<>();
        for (int position = 0; position <foodName.length; position++) {
            Food food = new Food();
            food.setName(foodName[position]);
            food.setPrice(foodPrice[position]);
            food.setDetail(foodDetail[position]);
            food.setPhoto(foodImage[position]);
            list.add(food);
        }
        return list;
    }
}