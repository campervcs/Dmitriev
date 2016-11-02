/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.terisproj;

/**
 *
 * @author Winter Fox
 */
public class Shape extends ShapeTable{
    
    private static final Shape[] shapes = new Shape[7];
    private static final String COLORS[] = new String[] { 
        "#FFFFFF", //БЕЛЫЙ
        "#C91365", //БОРДОВЫЙ
        "#E69E10", //ОРАНЖЕВЫЙ 
        "#F2F212", //ЖЕЛТЫЙ	
        "#1FE547", //ЗЕЛЕНЫЙ 
        "#10E6C9", //БИРЮЗОВЫЙ
        "#104DE6", //СИНИЙ
        "#72129F" //Фиолетовый
    };
    private static String LABELS = "IJLOSTZ";
    
    static{
        
		// 'I'
		int i = 0;
		ShapeTable shT = new ShapeTable(new int[][] { 
				new int[] { 0, 1, 0, 0 },
				new int[] { 0, 1, 0, 0 }, 
				new int[] { 0, 1, 0, 0 },
				new int[] { 0, 1, 0, 0 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'I', i);

		// 'J'
		shT = new ShapeTable(new int[][] { 
				new int[] { 2, 2, 0 },
				new int[] { 0, 2, 0 }, 
				new int[] { 0, 2, 0 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'J', i);

		// 'L'
		shT = new ShapeTable(new int[][] {
				new int[] { 0, 3, 0 },
				new int[] { 0, 3, 0 }, 
				new int[] { 3, 3, 0 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'L', i);

		// 'O'
		shT = new ShapeTable(new int[][] { 
				new int[] { 4, 4 }, 
				new int[] { 4, 4 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'O', i);

		// 'S'
		shT = new ShapeTable(new int[][] { 
				new int[] { 0, 5, 0 },
				new int[] { 5, 5, 0 }, 
				new int[] { 5, 0, 0 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'S', i);

		// 'T'
		shT = new ShapeTable(new int[][] { 
				new int[] { 0, 6, 0 },
				new int[] { 6, 6, 0 }, 
				new int[] { 0, 6, 0 } });
		shapes[i++] = new Shape(shT, COLORS[i], 'T', i);

		// 'Z'
		shT = new ShapeTable(new int[][] { 
				new int[] { 7, 0, 0 },
				new int[] { 7, 7, 0 }, 
				new int[] { 0, 7, 0 } });
		shapes[i] = new Shape(shT, COLORS[i], 'Z', i);
    }
    
    
    private int i;
    private char label;
    private String color;
    
    private Shape (ShapeTable shT, String color, char letter, int i){
        super(shT);
        this.i=i;
        this.label=label;
        this.color=color;
    }
    
    
    public Shape(Shape sh){
        super(sh);
        this.i = sh.i;
        this.label = sh.label;
        this.color = sh.color;
    }
    
    public static Shape get(int type) {
        if (type > 0 && type <= 7) {
                return shapes[type - 1];
        }
        return null;
    }
    
    public static Shape get(char letter) {
            return get(LABELS.indexOf(letter));
    }

    public static Shape getRandom() {
            return shapes[(int) (Math.random() * 5.0)];
    }

    public char getLabel() {
            return label;
    }

    public String getColor() {
            return color;
    }
    
}
