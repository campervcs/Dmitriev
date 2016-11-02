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


//Это один из способов работы с фигурами
public class ShapeTable {
    
    private static final int EMPTY_VALUE = 0;
    private int table[][];
    private int table_w, table_h;
    
    
    
    
    
    public ShapeTable (int width, int height){
        table_w=width;
        table_h=height;
        table = new int[table_w][table_h];
    }
    
    public ShapeTable(ShapeTable shT){
        table_w=shT.table_w;
        table_h=shT.table_h;
        table = new int[table_w][table_h];
        for(int x=0; x<table_w; x++)
        {
            for(int y=0; y<table_h; y++)
            {
                table[x][y]=shT.table[x][y];
            }
        }
    }
    
    public ShapeTable (int [][] data){
        table_h=data.length;
        table_w=data[0].length;
        table=data;
    }
    
    //Закрашиваем элементы по заданным начальным координатам и размерам
    public void fill(int px, int py, int w, int h, int value) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if ((px + x < table_w) && (py + y < table_h) && (px + x >= 0)
						&& (py + y >= 0)) {
					table[px + x][py + y] = value;
				}
			}
		}
	}
    
    //Вращать фигуру (поворчачиваем массив на 90 градусов)
    public void rotate() {
		int rotateArray[][] = new int[table_h][table_w];
		int s = table_h - 1;
		for (int i = 0; i < table_h; i++) {
			for (int j = 0; j < table_w; j++) {
				rotateArray[i][j] = table[j][s - i];
			}
		}
		table = rotateArray;
		int tmp = table_w;
		table_w = table_h;
		table_h = tmp;
	}

    public int getWidth() {
        return table_w;
    }

    public int getHeight() {
        return table_h;
    }

    //Копирование. Для того, чтобы перерисовывать падающую фигуру в новом месте (скопировав ее)
    public void copy(ShapeTable other, int px, int py) {

		int max_x = other.table_w;
		if (other.table_w + px > table_w)
			max_x = table_w - px;

		int max_y = other.table_h;
		if (max_y + py > table_h)
			max_y = table_h - py;

		for (int x = 0; x < max_x; x++)
			for (int y = 0; y < max_y; y++)
				if ((px + x < table_w) && (py + y < table_h) && (px + x >= 0)
						&& (py + y >= 0) && other.table[x][y] != EMPTY_VALUE)
					table[px + x][py + y] = other.table[x][y];
	}
    //Проверяет сможет ли данная фигура поместиться в текущем окне при появлении/повороте/сдвигах и тд
    public boolean fitsInto(ShapeTable other, int px, int py) {

		for (int x = 0; x < other.table_w; x++)
			for (int y = 0; y < other.table_h; y++) {
				if ((px + x < table_w) && (py + y < table_h) && (px + x >= 0)
						&& (py + y >= 0)) {
					if (table[px + x][py + y] != EMPTY_VALUE
							&& other.table[x][y] != EMPTY_VALUE) {
						return false;
					}
				} else if (other.table[x][y] != EMPTY_VALUE) {
					return false;
				}
			}
		return true;
	}

	public int get(int x, int y) {
		return table[x][y];
	}

	public void set(int x, int y, int value) {
		table[x][y] = value;
	}

	public boolean isEmpty(int x, int y) {
		return table[x][y] == EMPTY_VALUE;
	}
    
    
}
