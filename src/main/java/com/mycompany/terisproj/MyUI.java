package com.mycompany.terisproj;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hezamu.canvas.Canvas;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */





//Главный класс
@Theme("mytheme")
public class MyUI extends UI {
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    //Скорость изменения картинки/движения фигур и тд
    private static int TIME_TICK = 300;
    
    //Определим размеры каждой ячейки
    protected static final int TILE_SIZE = 30;
    
    //Определим размеры игрового поля
    private static int FIELD_HEIGHT=30;
    private static int FIELD_WIDTH=15;
    
    //Фон игрового поля (по-умолчанию черный)
    public static String BACK_COLOR="#000";
    
    //Пока не буду заморачиваться, сделаю просто в столбик все. Позже все будет красиво
    private VerticalLayout layout;
    //Выбрал Canvas, тк уже пользовался им
    private Canvas canvas;
    protected boolean running;
    //Позже будет класс, который будет содержать операции с игрой (Например перерисовка объектов, поля и тд.)
    //protected Game game;
    
    //Счетчик очков
    private Label score;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Создали вертикальный макет
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        //Добавил на экран
        setContent(layout);
        
        //Создадим кнопочки для движения и переворачивания фигур
        Button buttonLeft = new Button("Влево");
        //Честно еще не знаю полностью про лямбда выражения, он сам предложил заменить
        buttonLeft.addClickListener(e -> {
            //game.goLeft();
            //redrawGame();
        });
        //Для определения какую именно клавишу нажали
        buttonLeft.setClickShortcut(KeyCode.ARROW_LEFT);
        
        Button buttonRight = new Button("Вправо");
        buttonLeft.addClickListener(e -> {
            //game.goLeft();
            //redrawGame();
        });
        buttonLeft.setClickShortcut(KeyCode.ARROW_RIGHT);
        
        //Кнопка для поворота фигуры
        Button buttonRotate = new Button("Повернуть");
        buttonRotate.addClickListener(e -> {
            //game.rotate();
            //redrawGame();
        });
        buttonRotate.setClickShortcut(KeyCode.SPACEBAR);
        
        Button buttonFall = new Button("Кинуть вниз");
        buttonFall.addClickListener(e -> {
            //game.fall();
            //redrawGame();
        });
        
        
        layout.addComponent(new HorizontalLayout(
                buttonLeft, buttonRight, buttonFall, buttonRotate
        ));
        
        
        
        //final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    
}
