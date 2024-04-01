
package com.mycompany.duckduckgoose;

import java.util.Stack;



/**
 *This class is a game called Duck Duck Goose which takes the number of players
 * and their names along with the number of rounds to be played as inputs 
 * assuming that they would be placed in a circle. The last person is the IT 
 * person, the person who calls duck and goose. A random number will be 
 * generated to decide who will be called goose, and once called,
 * the goose and the IT person will move anticlockwise and clockwise 
 * respectively along the circle of players until they reach the goose's initial
 * position. The speed is also decided randomly for both and the one who reaches 
 * first is the winner and the loser becomes the new IT person.
 * 
 * @author Hrittija Bhattacharjee
 * ID: 1690727
 * Date: 19 March 2023
 * AUSCI 235
 */
public class DuckDuckGoose {
    static DCircLinkList <String> list = new DCircLinkList();
    static Stack<DNode> stack = new Stack();
        

    public static void main(String[] args) {
        DCircLinkList<String> foo = new DCircLinkList();
        foo.addLast("Priscilla");
        foo.addLast("James");
        foo.addLast("Thai");
        foo.addLast("Zachary");
        foo.addLast("Tina");
        foo.addLast("Anjola");
        foo.addLast("Tony");
        foo.addLast("Tinku");
        middlePrintPaths(foo);
        
   }
    
    public static DNode<String> middlePrintPaths(DCircLinkList<String> list){
        DNode<String> middle= null;

        if (list.getSize() % 2 ==0){
            for(int i = 0; i < list.getSize()/2; i++){
                middle = list.getFirstNode();
                System.out.print(list.getFirstElement() + " ");
                list.rotateCCW(); //keep rotating and printing the name
            }
            list.rotateCW();
            System.out.println();
            for(int i = 0; i < list.getSize()/2 +1; i++){
                stack.push(list.getFirstNode());
                list.rotateCCW(); 

            }
            while (!stack.isEmpty()){
                middle = stack.pop();
                System.out.print(middle.getElement() + " ");
            }
        }
        else if (list.getSize() == 0){
            middle = null;

        }
        else{
            for(int i = 0; i < list.getSize()/2 + 1; i++){
                middle = list.getFirstNode();
                System.out.print(middle.getElement() + "  ");
                list.rotateCCW(); //keep rotating and printing the name
            }
            list.rotateCW();
            System.out.println();
            for(int i = 0; i < list.getSize()/2+1; i++){

                stack.push(list.getFirstNode());
                list.rotateCCW(); //keep rotating and printing the name
            }
            while (!stack.isEmpty()){
                middle = stack.pop();
                System.out.print(middle.getElement() + " ");
            }
        }
        return middle;    

    }

    public static DNode<String> printaltNodes(DCircLinkList<String> list){
        DNode<String> middle= null;
        if (list.getSize() % 2 == 0){
            for(int i = 0; i < list.getSize()/2; i++){
                middle = list.getFirstNode();
                System.out.print(list.getFirstElement() + " ");
                list.rotateCCW(); //keep rotating and printing the name
                list.rotateCCW();
            }
            list.rotateCCW();
            System.out.println();
            for(int i = 0; i < list.getSize()/2; i++){

                middle = list.getFirstNode();
                System.out.print(list.getFirstElement() + " ");

                list.rotateCCW();
                list.rotateCCW();
                        

        }
        }
        else{
            for(int i = 0; i < list.getSize()/2+1; i++){
                middle = list.getFirstNode();
                System.out.print(list.getFirstElement() + " ");
                list.rotateCCW(); //keep rotating and printing the name
                list.rotateCCW();
            }
            System.out.println();
            for(int i = 0; i < list.getSize()/2; i++){
                middle = list.getFirstNode();
                System.out.print(list.getFirstElement() + " ");

                list.rotateCCW();
                list.rotateCCW();
        }
    }
        return null;
    }

    public static void printreverse(DCircLinkList<String> list){
        for(int i = 0; i < list.getSize(); i++){
            stack.push(list.getFirstNode());
            list.rotateCCW();
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop().getElement() + " ");
        }
        
            }

}