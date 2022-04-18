import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsWindow extends  JFrame {
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 300;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size: ";
    private static final String WIN_LENGTH_PREFIX = "Win length: ";

    private JRadioButton humanVsAi;
    private JRadioButton humanVsHuman;
    private JSlider winLengthSlider;
    private JSlider fieldSizeSlider;
    private GameWindow gameWindow;

    public SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(gameWindow); //позиционируем размещение окна настроек относительно основного gameWindow
        setResizable(false); //окно настроек не ресайзится
        setTitle("Settings");
        setLayout(new GridLayout(10,1)); //размечаем поля для объектов управления. 1 столбец и 10 элементов-строк
        JButton buttonStart = new JButton("START");
        buttonStart.addActionListener((e -> submitSettings(gameWindow)));
        addFieldSize();
        addGameMode();
        add(buttonStart);


    }

    private void addFieldSize() {
        JLabel labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);// надпись над первым слайдером - управляющим размером поля
        JLabel labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH); //надпись над вторым слайдером - управляющим длиной победы
        fieldSizeSlider = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE); // создаем слайдер управления размером поля. Указываем МИН, Макс, стартовое значение
        winLengthSlider = new JSlider(MIN_WIN_LENGTH, MAX_FIELD_SIZE, MIN_WIN_LENGTH); // создаем слайдер управления длиной победы. Указываем МИН, Макс, стартовое значение

        fieldSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int current = fieldSizeSlider.getValue(); //в переменную помещаем значение установленное на fieldSizeSlider
                labelFieldSize.setText(FIELD_SIZE_PREFIX + current); // в labelFieldSize показываем значение установленное на слайдере fieldSizeSlider
                winLengthSlider.setMaximum(current);
            }
        });
        winLengthSlider.addChangeListener((e -> labelWinLength.setText(WIN_LENGTH_PREFIX + winLengthSlider.getValue())));
//строкой выше прописали зависимость значения win слайдера от значения feldSize слайдера.
        add(new JLabel("Choose field size")); //добавили надпись над слайдером 1
        add(labelFieldSize); //добавили значение слайдера field
        add(fieldSizeSlider); //добавляем field слайдер на панель
        add(new JLabel("Choose win length")); //добавили надпись над слайдером 1
        add(labelWinLength); //добавили значение слайдера win
        add(winLengthSlider); //добавляем field слайдер на панель

    }

    private void addGameMode() {
        add(new JLabel("Choose game mode")); //надпись нал РадоБаттонами.
        humanVsAi = new JRadioButton("Human vs AI", true); //добавили радибатон, true означает выбрана по умолчанияю
        humanVsHuman = new JRadioButton("Human vs human");
        ButtonGroup group = new ButtonGroup();// создаем связь между двумя радибатонами, которая позволит выбрать только один из них
        group.add(humanVsAi);
        group.add(humanVsHuman);
        add(humanVsAi);
        add(humanVsHuman);
    }

    private void submitSettings(GameWindow gameWindow) {
        int gameMode;
        //далее в цикле мы присваеваем gameMode значение одной из констант MODE в зависимости от значени радиобатона.
        // т.к. радиобатонов только 2 значение false нам автоматом сообщает о выборе HumanVsHuman
        if (humanVsAi.isSelected()) {
            gameMode = GameMap.MODE_VS_AI;
        } else {
            gameMode = GameMap.MODE_VS_HUMAN;
        }
        int fieldSize = fieldSizeSlider.getValue();
        int winLength = winLengthSlider.getValue();
        gameWindow.startGame(gameMode, fieldSize, winLength);//запускаем игру, передаем в стартовый менеджер выбранные настройки
        setVisible(false); //скрываем панель настроек, она нам больше не нужна.
    }
}
