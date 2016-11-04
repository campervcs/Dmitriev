package com.mycompany.terisproj;

import com.vaadin.annotations.Push;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
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
@Title("Vaadin Tetris")
@Push
@Theme("mytheme")
public class MyUI extends UI {
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    //Скорость изменения картинки/движения фигур и тд
    private static int TIME_TICK = 500;
    
    //Определим размеры каждой ячейки
    protected static final int TILE_SIZE = 30;
    
    //Определим размеры игрового поля
    private static int FIELD_HEIGHT=20;
    private static int FIELD_WIDTH=10;
    
    //Фон игрового поля (по-умолчанию черный)
    public static String PLAYGROUND_COLOR="#000";
    
    //Пока не буду заморачиваться, сделаю просто в столбик все. Позже все будет красиво
    private VerticalLayout layout;
    //Выбрал Canvas, тк уже пользовался им
    private Canvas canvas;
    protected boolean running;
    //Позже будет класс, который будет содержать операции с игрой (Например перерисовка объектов, поля и тд.)
    protected Game game = new Game(FIELD_WIDTH, FIELD_HEIGHT);
    
    //Счетчик очков
    private Label score;
    private Label difficultLabel;
    int difficult=5;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Создали вертикальный макет
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        //Добавил на экран
        setContent(layout);
        
        
        
        final Button difUpButton = new Button("Увеличить сложность");
        difUpButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                TIME_TICK-=200; difficult-=2;
                if (TIME_TICK<=-100) {TIME_TICK+=200; difficult+=2;}
                difReload();
            }
        });
        final Button difDownButton = new Button("Уменьшить сложность");
        difDownButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                TIME_TICK+=200; difficult+=2;
                if (TIME_TICK>=1100) {TIME_TICK-=200; difficult-=2;}
                difReload();
            }
        });
        
        
        
        //Создадим кнопочки для движения и переворачивания фигур
        final Button buttonLeft = new Button("Влево");
        buttonLeft.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                game.goLeft();
                redrawGame();
            }
        });
        
        final Button buttonRight = new Button("Вправо");
        buttonRight.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                game.goRight();
                redrawGame();
            }
        });
        
        //Кнопка для поворота фигуры
        final Button buttonRotate = new Button("Повернуть");
        buttonRotate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                game.rotate();
                redrawGame();
            }
        });
        
        final Button buttonFall = new Button("Кинуть вниз");
        buttonFall.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                game.fall();
                redrawGame();
            }
        });
        
        final Button dropBtn = new Button("Кинуть вниз");
        dropBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                game.fall();
                redrawGame();
            }
        });
        
        
        final Button restartBtn = new Button("Старт игры");
        restartBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent e) {
                running = !running;
                if (running) {
                    TIME_TICK=500;
                    game = new Game(FIELD_WIDTH, FIELD_HEIGHT);
                    startGameThread();
                    dropBtn.focus();
                } else {
                    endOfGame();
                }
            }
        });
        
        
        score = new Label("");
        score.setWidth("150");
        difficultLabel=new Label ("");
        difficultLabel.setWidth("150");
        
        layout.addComponent(new HorizontalLayout(
                restartBtn, buttonLeft, buttonRight, buttonFall, buttonRotate,difUpButton, difDownButton
        ));
        
        layout.addComponent(new HorizontalLayout(
                difficultLabel, score
        ));
        
        
        //Создадим экземпляр canvas
        canvas =new Canvas();
        layout.addComponent(canvas);
        //Укжаем его размеры
        canvas.setWidth((TILE_SIZE * FIELD_WIDTH)+"px");
        canvas.setHeight((TILE_SIZE * FIELD_HEIGHT)+"px");
        
        
        
    }

    //Поток для работы игры с шагами для паузы (чтобы не моментально падали блоки)
    protected synchronized void startGameThread() {
        Thread thread = new Thread() {
            public void run(){
                while (running && !game.isEndOfGame()){
                    redrawGame();
                    try{
                        sleep(TIME_TICK);
                    }
                    catch(InterruptedException ie){}
                    
                    game.oneMove();
                    scoreUp();
                    difReload();
                }
                endOfGame();
            }
        };
        thread.start();
    }
    
    protected synchronized void difReload(){
        difficultLabel.setValue("   Сложность: "+difficult);
    }
    protected synchronized void scoreUp(){
        access(() -> {
            score.setValue("    Счет: " + game.getScore());
        });
    }
    
    protected synchronized void endOfGame() {
        running = false;
        access(() -> {
        Notification.show("Конец игры", "У вас очков: "  + game.getScore(), Type.HUMANIZED_MESSAGE);
        });
    }
    
    protected synchronized void redrawGame()
    {
        access(() ->{
        canvas.clear();
        canvas.setFillStyle(PLAYGROUND_COLOR);
        canvas.fillRect(0, 0, game.getWidth() * TILE_SIZE + 2, game.getHeight() * TILE_SIZE + 2);
        
        ShapeTable shT = game.getStateNow();
        for (int x=0;x<shT.getWidth();x++){
            for(int y=0; y<shT.getHeight();y++){
                
                int currentTile = shT.get(x, y);
                if(currentTile>0){
                    
                    String color = Shape.get(currentTile).getColor();
                    canvas.setFillStyle(color);
                    canvas.fillRect(x * TILE_SIZE + 1, y * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
                }
            }
        }
    });
    }
    
    
}
