package net.middlemind.MemoryMatch_Chapter7_Challenge2;

import java.util.ArrayList;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameType;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.Screen;
import java.util.Random;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgContainer;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgRect;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A game screen object, ScreenGame, that extends the MmgGameScreen base class.
 * This class is for testing new UI widgets, etc. Created by Middlemind Games
 * 03/15/2020
 *
 * @author Victor G. Brusca
 */
@SuppressWarnings({"FieldMayBeFinal", "FieldNameHidesFieldInSuperclass", "NonPublicExported", "UnusedAssignment"})
public class ScreenGame extends Screen {

    /*********************************************************
    ****************** CHAPTER 7 CHALLENGE 2 *****************
    **********************************************************
    * Description: 
    * Find the package, net.middlemind.MemoryMatch_Chapter7_Challenge2, and open the ScreenGame.java file.
    * We’ll continue on the same track and implement our own code to experiment with animating, moving image 
    * in response to user input. Choose four different keyboard keys that aren’t used by the game. Two of 
    * these keys will be used to toggle your new image’s visibility. The other two will be used to move your 
    * image left and right.
    * 
    * In order to handle user input, you’ll need to check for your target keys in the following methods:
    * 
    *   ProcessKeyPress
    *   ProcessKeyRelease
    * 
    * It’s up to you to use the GetPosition, SetPosition methods of the MmgBmp class to control the image’s position. 
    * You’ll also need to use the GetIsVisible, SetIsVisible methods to control the image’s visibility. 
    * You should be familiar with the keyboard methods and the MmgBmp class methods from your experiencing using 
    * the class in previous challenges. You must run this package's file - MemoryMatch.java - right-click and select 
    * Run-File, to test the game.
    * 
    * Clue:
    * You can rely on existing code in the ProcessKeyPress and ProcessKeyRelease methods to figure out how to 
    * support input for you four chosen keyboard keys. You can figure out how to use the MmgBmp class’ methods 
    * by researching their use in other parts of the ScreenGame class. Perform a text search to figure out how 
    * to use the methods. You can add more class fields is need be to help manage the movement and positioning 
    * of our image.
    */
    
    /**
     * An enumeration that tracks which number is visible during game start
     * countdown and in-game countdown.
     */
    private enum NumberState {
        NONE,
        NUMBER_1,
        NUMBER_2,
        NUMBER_3
    };

    /**
     * An enumeration that tracks which state this Screen is currently
     * rendering. Allows the Screen to support different views like in-game,
     * countdown, game over, game start, etc.
     */
    private enum State {
        NONE,
        SHOW_GAME,
        SHOW_COUNT_DOWN,
        SHOW_COUNT_DOWN_IN_GAME,
        SHOW_GAME_OVER,
        SHOW_GAME_EXIT,
        SHOW_GAME_START
    };

    /**
     * An enumeration that indicates the size of the game board.
     */
    private enum GameBoardSize {
        NONE,
        SMALL,
        MEDIUM,
        LARGE
    };

    /**
     * A class variable that indicates the size of the game board.
     */
    private GameBoardSize gameBoardSize = GameBoardSize.SMALL;

    /**
     * Represents the type of game that is running.
     */
    private GameType gameType = GameType.GAME_ONE_PLAYER;

    /**
     * Represents the game screen's previous internal state.
     */
    private State statePrev = State.NONE;

    /**
     * Represents the game screen's current internal state.
     */
    private State state = State.NONE;

    /**
     * Represents the game screen's current countdown number state.
     */
    private NumberState numberState = NumberState.NONE;

    /**
     * The number of milliseconds the countdown number has been displayed.
     */
    private long timeNumberMs = 0L;

    /**
     * The number of milliseconds the countdown number should be displayed.
     */
    private long timeNumberDisplayMs = 1000;

    /**
     * A temporary timing variable.
     */
    private long timeTmpMs = 0L;

    /**
     * The background image used for the main game screen.
     */
    private MmgBmp bground;

    /**
     * An MmgBmp image that represents the number 1.
     */
    private MmgBmp number1;

    /**
     * An MmgBmp image that represents the number 2.
     */
    private MmgBmp number2;

    /**
     * An MmgBmp image that represents the number 3.
     */
    private MmgBmp number3;

    /**
     * The number of matches you need to win the game.
     */
    private int scoreGameWin = 8;

    //Player 1, Blue, Left    
    /**
     * The title of the left-hand side score section.
     */
    private MmgFont scoreLeftTitle;
    
    /**
     * The score of the left player.
     */
    private int scorePlayerLeft = 0;    
    
    /**
     * The number of turns the left-hand side player has taken.
     */
    private int turnsPlayerLeft = 0;
    
    /**
     * The left hand side score display.
     */
    private MmgFont scoreLeft;
    
    /**
     * The left-hand player's info, turns label.
     */
    private MmgFont turnsLeft;    

    /**
     * The title of the right-hand side score section.
     */
    private MmgFont scoreRightTitle;    
    
    //Player 2, Green, Right
    /**
     * The score of the right player.
     */
    private int scorePlayerRight = 0;
    
    /**
     * The number of turns the right-hand side player has taken.
     */
    private int turnsPlayerRight = 0;    

    /**
     * The right hand side score display.
     */
    private MmgFont scoreRight;

    /**
     * The right-hand player's info, turns label.
     */
    private MmgFont turnsRight;    
    
    /**
     * An MmgFont object used to represent the exit text.
     */
    private MmgFont exit;

    /**
     * An MmgBmp image used as the background of the exit text.
     */
    private MmgBmp exitBground;

    /**
     * The position of the exit message on the popup background.
     */
    private MmgVector2 exitPosPopupBground;
    
    /**
     * The position of the exit message background image on the popup background.
     */
    private MmgVector2 exitBgroundPosPopupBground;    
    
    /**
     * The position of the exit message on the game background.
     */
    private MmgVector2 exitPosGameBground;    
        
    /**
     * The position of the exit message background image on the game background.
     */
    private MmgVector2 exitBgroundPosGameBground;        
    
    /**
     * A random number generator.
     */
    private Random rand;

    /**
     * The game screen panel's current position.
     */
    private MmgVector2 screenPos;

    /**
     * An MmgBmp image that is used as the source for a 9 sliced popup window.
     */
    private MmgBmp bgroundPopupSrc;

    /**
     * An Mmg9Slice object used as the game's main background window.
     */
    private Mmg9Slice bgroundMainPopup;

    /**
     * An integer representing the width of the main popup background image.
     */
    private int bgroundMainPopupWidth;

    /**
     * An integer representing the height of the main popup background image.
     */
    private int bgroundMainPopupHeight;

    /**
     * Text used in dialogues.
     */
    private MmgFont txtOk;

    /**
     * Text used in dialogues.
     */
    private MmgFont txtCancel;

    /**
     * Text used in the game's goal.
     */
    private MmgFont txtGoal;

    /**
     * Text used in the game's instructions.
     */
    private MmgFont txtDirecP1;

    /**
     * Text used in the game's instructions.
     */
    private MmgFont txtDirecP2;

    /**
     * Text used on the game over screen.
     */
    private MmgFont txtGameOver1;

    /**
     * Text used on the game over screen.
     */
    private MmgFont txtGameOver2;

    /**
     * Padding used in the UI positioning.
     */
    private int padding = MmgHelper.ScaleValue(4);

    /**
     * The turn count of the left hand player.
     */
    private int turnCntPlayerLeft = 0;

    /**
     * The turn count of the right hand player.
     */
    private int turnCntPlayerRight = 0;

    /**
     * The memory match game logo image.
     */
    private MmgBmp mmmLogo;
    
    /**
     * A much smaller version of the memory match game's logo.
     */
    private MmgBmp mmmLogoMini;

    /**
     * Text used to prompt the user for the desired board size.
     */
    private MmgFont boardSizePrompt;

    /**
     * Text used to represent the small board size.
     */
    private MmgFont boardSizeSmall;

    /**
     * Text used to represent the medium board size.
     */
    private MmgFont boardSizeMedium;

    /**
     * Text used to represent the large board size.
     */
    private MmgFont boardSizeLarge;

    /**
     * An integer representing the chosen board size.
     */
    private int boardSizeSelectedIdx;

    /**
     * Text representing the button used to start the game when in the game start state.
     */
    private MmgFont gameStartPrompt;

    /**
     * Text representing the game instructions prompt.
     */
    private MmgFont gameInstructionsPrompt;

    /**
     * Text representing the game instructions line 1.
     */
    private MmgFont gameInstructions1;

    /**
     * Text representing the game instructions line 2.
     */
    private MmgFont gameInstructions2;

    /**
     * Text representing the game instructions line 3.
     */
    private MmgFont gameInstructions3;

    /**
     * Text representing the game instructions line 4.
     */
    private MmgFont gameInstructions4;

    /**
     * A float value representing the percentage of the full screen size to use
     * for the main background popup image.
     */
    private float bgroundMainPopupPrct = 0.75f;

    /**
     * A container used to hold images and text drawn on the back layer.
     */
    private MmgContainer layerBack;

    /**
     * A container used to hold images and text drawn on the middle layer.
     */
    private MmgContainer layerMiddle;

    /**
     * A container used to hold images and text drawn on the front layer.
     */
    private MmgContainer layerFront;

    /**
     * A container used to hold the cards on their own layer.
     */
    private MmgContainer layerCards;
    
    /**
     * An image used to indicate which player's turn it is.
     */
    private MmgBmp currentPlayerMarker;

    /**
     * The position to place the marker for the left-hand player.
     */
    private MmgVector2 currentPlayerMarkerPosLeft;
    
    /**
     * The position to place the marker for the right-hand player.
     */
    private MmgVector2 currentPlayerMarkerPosRight;    
    
    /**
     * The number of clicks for the current player this turn.
     */
    private int currentPlayerClicks;
    
    /**
     * A flag indicating if the current player is the right-hand player.
     */
    private boolean currentPlayerMarkerIsRight;
    
    /**
     * A flag indicating if mouse input should be frozen.
     */
    private boolean clickFreeze;
    
    /**
     * A list of the clicked cards this turn.
     */
    private ArrayList<MemoryItem> clickedCards;
    
    /**
     * An image used to draw a red X in the right-hand player's area.
     */
    private MmgBmp redXRight;
    
    /**
     * An image used to draw a red X in the left-hand player's area.
     */
    private MmgBmp redXLeft;
    
    /**
     * A flag indicating if the turn results should be shown.
     */
    private long showResultStart;
    
    /**
     * A number indicating how long the turn results should flash when shown.
     */
    private long showResultDuration = 1000;
    
    /**
     * A green check mark to display over the right-hand player's area when they get a match. 
     */
    private MmgBmp greenCheckRight;
    
    /**
     * A green check mark to display over the left-hand player's area when they get a match.
     */
    private MmgBmp greenCheckLeft;
    
    /*********************************************************
    ********* CHAPTER 7 CHALLENGE 1 SOLUTION PART 1 **********
    **********************************************************
    * Solution: 
    * Add the class field, myImg, with a data type of MmgBmp as
    * instructed.
    */
    
    /**
     * A custom image we're going to add to the game.
     */
    public MmgBmp myImg;
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenGame(GameStates State, GamePanel Owner) {
        super(State, Owner);
        clickedCards = new ArrayList();
        MemoryItemList.Init();
        pause = false;
        ready = false;
        owner = Owner;
    }

    /**
     * Initialize the containers for the different game layers.
     */
    private void InitGameLayerContainers() {
        layerBack = new MmgContainer();
        AddObj(layerBack);

        layerMiddle = new MmgContainer();
        AddObj(layerMiddle);

        layerCards = new MmgContainer();
        AddObj(layerCards);
        layerCards.SetIsVisible(false);
                
        layerFront = new MmgContainer();
        AddObj(layerFront);
    }

    /**
     * Initialize the screen dimensions and position.
     */
    private void InitScreenDimensionsAndPosition() {
        //Set screen dimensions and position
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        screenPos = GetPosition();
        MmgHelper.wr("ScreenPos: " + screenPos.ApiToString());
    }

    /**
     * Initialize the main background popup image dimensions.
     */
    private void InitMainBackgroundPopupDimensions() {
        //Set main background popup dimensions
        bgroundMainPopupWidth = MmgHelper.ScaleValue((int) (GetWidth() * bgroundMainPopupPrct));
        bgroundMainPopupHeight = MmgHelper.ScaleValue((int) (GetHeight() * bgroundMainPopupPrct));
        MmgHelper.wr("InitMainBackgroundPopupDimensions: dimensions: " + bgroundMainPopupWidth + "x" + bgroundMainPopupHeight);
    }

    /**
     *
     */
    private void InitGameGeneralResources() {
        MmgBmp lval = null;
        String file = "";
        MmgSound sval = null;
        int tmpW = 0;
        int tmpH = 0;

        /////////////////////////////////////////////////////Load game board background image
        file = "memory_match_board.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        bground = lval;
        if (bground != null) {
            MmgHelper.CenterHorAndVert(bground);
            bground = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("gameBoard", bground, classConfig, bground.GetPosition());
            layerBack.Add(bground);
            bground.SetIsVisible(false);            
        }

        //load red, error, X for left player
        file = "error.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        redXLeft = lval;
        if (redXLeft != null) {
            redXLeft.SetPosition(10, bground.GetPosition().GetY());
            layerFront.Add(redXLeft);
            redXLeft.SetIsVisible(false);            
        }
        
        //load red, error, X for right player
        file = "error.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        redXRight = lval;
        if (redXRight != null) {
            redXRight.SetPosition(w - redXRight.GetWidth() - 10, bground.GetPosition().GetY());
            layerFront.Add(redXRight);
            redXRight.SetIsVisible(false);            
        }        
        
        //load green, correct, check for left player
        file = "correct.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        greenCheckLeft = lval;
        if (greenCheckLeft != null) {
            greenCheckLeft.SetPosition(10, bground.GetPosition().GetY());
            layerFront.Add(greenCheckLeft);
            greenCheckLeft.SetIsVisible(false);            
        }
        
        //load green, correct, check for right player
        file = "correct.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        greenCheckRight = lval;
        if (greenCheckRight != null) {
            greenCheckRight.SetPosition(w - greenCheckRight.GetWidth() - 10, bground.GetPosition().GetY());
            layerFront.Add(greenCheckRight);
            greenCheckRight.SetIsVisible(false);            
        }        
        
        //TODO: load new soundFx
        //Load bounce normal sound
        file = "jump1.wav";
        sval = MmgHelper.GetBasicCachedSound(file);
        //bounceNorm = sval;

        //TODO: load new soundFx
        //Load bounce super sound
        file = "jump2.wav";
        sval = MmgHelper.GetBasicCachedSound(file);
        //bounceSuper = sval;  

        /////////////////////////////////////////////////////Load score left text
        scoreLeftTitle = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeftTitle.SetText("Player1");
        scoreLeftTitle.SetMmgColor(MmgColor.GetBlueGray());
        scoreLeftTitle.SetPosition(MmgHelper.ScaleValue(20), screenPos.GetY() + scoreLeftTitle.GetHeight() + MmgHelper.ScaleValue(5));
        layerMiddle.Add(scoreLeftTitle);
        scoreLeftTitle.SetIsVisible(false);
        
        scoreLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeft.SetText("Matches: 00");
        scoreLeft.SetMmgColor(MmgColor.GetBlueGray());
        scoreLeft.SetPosition(MmgHelper.ScaleValue(20), screenPos.GetY() + scoreLeft.GetHeight() + MmgHelper.ScaleValue(25));
        layerMiddle.Add(scoreLeft);        
        scoreLeft.SetIsVisible(false);
        
        turnsLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        turnsLeft.SetText("Turns: 00");
        turnsLeft.SetMmgColor(MmgColor.GetBlueGray());
        turnsLeft.SetPosition(MmgHelper.ScaleValue(20), screenPos.GetY() + turnsLeft.GetHeight() + MmgHelper.ScaleValue(45));
        layerMiddle.Add(turnsLeft);        
        turnsLeft.SetIsVisible(false);

        /////////////////////////////////////////////////////Load score right text   
        scoreRightTitle = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRightTitle.SetText("Player2");
        scoreRightTitle.SetMmgColor(MmgColor.GetLimeGreen());
        scoreRightTitle.SetPosition(w - scoreRightTitle.GetWidth() - MmgHelper.ScaleValue(20), screenPos.GetY() + scoreRightTitle.GetHeight() + MmgHelper.ScaleValue(5));
        layerMiddle.Add(scoreRightTitle);
        scoreRightTitle.SetIsVisible(false);        
        
        scoreRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRight.SetText("Matches: 00");
        scoreRight.SetMmgColor(MmgColor.GetLimeGreen());
        scoreRight.SetPosition(w - scoreRight.GetWidth() - MmgHelper.ScaleValue(20), screenPos.GetY() + scoreRight.GetHeight() + MmgHelper.ScaleValue(25));
        layerMiddle.Add(scoreRight);
        scoreRight.SetIsVisible(false);
        
        turnsRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        turnsRight.SetText("Turns: 00");
        turnsRight.SetMmgColor(MmgColor.GetLimeGreen());
        turnsRight.SetPosition(w - turnsRight.GetWidth() - MmgHelper.ScaleValue(20), screenPos.GetY() + turnsRight.GetHeight() + MmgHelper.ScaleValue(45));
        layerMiddle.Add(turnsRight);        
        turnsRight.SetIsVisible(false);                
        
        /////////////////////////////////////////////////////Load string exit text
        file = "Press 'B' to Exit";
        exit = MmgFontData.CreateDefaultBoldMmgFontLg();
        exit.SetText(file);
        exit.SetMmgColor(MmgColor.GetRed());
        exit.SetPosition((w - exit.GetWidth()) / 2, screenPos.GetY() + h - exit.GetHeight() - MmgHelper.ScaleValue(45));

        /////////////////////////////////////////////////////Load exit text background image
        tmpW = exit.GetWidth() + (padding * 2);
        tmpH = exit.GetHeight() + (padding * 2);        
        exitBground = MmgHelper.CreateFilledBmp(tmpW, tmpH, MmgColor.GetBlack());
        exitBground.SetPosition(exit.GetX() - padding, exit.GetY() - exit.GetHeight());
        exitPosPopupBground = exit.GetPosition();
        exitBgroundPosPopupBground = exitBground.GetPosition();

        exitPosGameBground = exit.GetPosition().Clone();
        exitPosGameBground.SetY(exitPosGameBground.GetY() + MmgHelper.ScaleValue(58));
        exitBgroundPosGameBground = exitBground.GetPosition().Clone();
        exitBgroundPosGameBground.SetY(exitBgroundPosGameBground.GetY() + MmgHelper.ScaleValue(61));

        layerFront.Add(exitBground);
        exitBground.SetIsVisible(false);        
        layerFront.Add(exit);
        exit.SetIsVisible(false);
        
        /////////////////////////////////////////////////////Load small popup window exit text
        file = "Exit Game (A)";
        txtOk = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtOk.SetText(file);
        txtOk.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtOk);
        txtOk.SetY(txtOk.GetY() + MmgHelper.ScaleValue(20) - MmgHelper.ScaleValue(15));
        layerFront.Add(txtOk);
        txtOk.SetIsVisible(false);
        
        /////////////////////////////////////////////////////Load popup window cancel text
        file = "Cancel Exit (B)";
        txtCancel = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtCancel.SetText(file);
        txtCancel.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtCancel);
        txtCancel.SetY(txtCancel.GetY() + MmgHelper.ScaleValue(20) + MmgHelper.ScaleValue(15));
        layerFront.Add(txtCancel);        
        txtCancel.SetIsVisible(false);
        
        file = "card_back_32x32.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        currentPlayerMarker = lval;
        if (currentPlayerMarker != null) {
            MmgHelper.CenterHorAndVert(currentPlayerMarker);
            currentPlayerMarker = MmgBmpScaler.ScaleMmgBmp(currentPlayerMarker, new MmgVector2(20, 20), true);
            currentPlayerMarker.SetIsVisible(false);
            layerMiddle.Add(currentPlayerMarker);
        }
        
        currentPlayerMarkerPosLeft = new MmgVector2(scoreLeftTitle.GetX() + scoreLeftTitle.GetWidth() + MmgHelper.ScaleValue(3), scoreLeftTitle.GetY() - MmgHelper.ScaleValue(17));
        currentPlayerMarkerPosRight = new MmgVector2(scoreRightTitle.GetX() - currentPlayerMarker.GetWidth() - MmgHelper.ScaleValue(3), scoreRightTitle.GetY() - MmgHelper.ScaleValue(17));
    }

    /**
     *
     */
    private void InitGameStateResourcesCountDown() {
        MmgBmp lval = null;
        String file = "";
        
        /////////////////////////////////////////////////////Load number one image
        file = "num_1_lrg.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        number1 = lval;
        if (number1 != null) {
            MmgHelper.CenterHorAndVert(number1);
            number1 = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("numberOne", number1, classConfig, number1.GetPosition());
            number1.SetIsVisible(false);
            layerFront.Add(number1);
        }

        /////////////////////////////////////////////////////Load number two image
        file = "num_2_lrg.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        number2 = lval;
        if (number2 != null) {
            MmgHelper.CenterHorAndVert(number2);
            number2 = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("numberTwo", number2, classConfig, number2.GetPosition());
            number2.SetIsVisible(false);
            layerFront.Add(number2);
        }

        /////////////////////////////////////////////////////Load number three image
        file = "num_3_lrg.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        number3 = lval;
        if (number3 != null) {
            MmgHelper.CenterHorAndVert(number3);
            number3 = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("numberThree", number3, classConfig, number3.GetPosition());
            number3.SetIsVisible(false);
            layerFront.Add(number3);
        }        

        /////////////////////////////////////////////////////Load string game win condition
        if(this.gameType == GameType.GAME_TWO_PLAYER) {
            file = "Goal: Get the most matches in as few turns as possible.";
        } else {
            file = "Goal: Find all the matches in as few turns as possible.";
        }
        txtGoal = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGoal.SetText(file);
        txtGoal.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtGoal);
        txtGoal.SetY(number1.GetY() - txtGoal.GetHeight() + MmgHelper.ScaleValue(5));
        layerFront.Add(txtGoal);
        txtGoal.SetIsVisible(false);

        /////////////////////////////////////////////////////Load string player 1 directions
        file = "Use the mouse to click on cards and find a match.";
        txtDirecP1 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtDirecP1.SetText(file);
        txtDirecP1.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtDirecP1);
        txtDirecP1.SetY(number1.GetY() + number1.GetHeight() + txtDirecP1.GetHeight() + MmgHelper.ScaleValue(10));
        layerFront.Add(txtDirecP1);        
        txtDirecP1.SetIsVisible(false);

        /////////////////////////////////////////////////////Load string player 2 directions
        file = "The card back icon next to the player indicates who's turn it is.";
        txtDirecP2 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtDirecP2.SetText(file);
        txtDirecP2.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtDirecP2);
        txtDirecP2.SetY(txtDirecP1.GetY() + txtDirecP1.GetHeight() + MmgHelper.ScaleValue(10));
        layerFront.Add(txtDirecP2);        
        txtDirecP2.SetIsVisible(false);
    }

    /**
     *
     */
    private void InitGameStateResourcesGameOver() {
        String file = "";

        /////////////////////////////////////////////////////Load game over player 1 text
        file = "Player 1 has won the game. Press A or B to exit.";
        txtGameOver1 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGameOver1.SetText(file);
        txtGameOver1.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtGameOver1);
        layerFront.Add(txtGameOver1);        
        txtGameOver1.SetIsVisible(false);

        /////////////////////////////////////////////////////Load game over player 2 text
        file = "Player 2 has won the game. Press A or B to exit.";
        txtGameOver2 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGameOver2.SetText(file);
        txtGameOver2.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtGameOver2);
        layerFront.Add(txtGameOver2);        
        txtGameOver2.SetIsVisible(false);
    }

    /**
     *
     */
    private void InitGameStateResourcesPopups() {
        MmgBmp lval = null;
        String file = "";

        file = "popup_window_base_64.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        bgroundPopupSrc = lval;
        if (bgroundPopupSrc != null) {
            /////////////////////////////////////////////////////Scale popup base image
            MmgHelper.CenterHorAndVert(bgroundPopupSrc);
            bgroundPopupSrc = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("popupWindowBase", bgroundPopupSrc, classConfig, bgroundPopupSrc.GetPosition());

            /////////////////////////////////////////////////////Set main background popup 9 slice image
            bgroundMainPopup = new Mmg9Slice(16, bgroundPopupSrc, bgroundMainPopupWidth, bgroundMainPopupHeight);
            bgroundMainPopup.SetPosition(MmgVector2.GetOriginVec());
            MmgHelper.CenterHorAndVert(bgroundMainPopup);
            bgroundMainPopup.GetPosition().SetX(bgroundMainPopup.GetPosition().GetX() - MmgHelper.ScaleValue(5));
            layerFront.Add(bgroundMainPopup);
            bgroundMainPopup.SetIsVisible(false);
        }
    }

    /**
     *
     */
    private void InitGameStateResourcesStart() {
        MmgBmp lval = null;
        String file = "";

        file = "mmm_game_title.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        mmmLogo = lval;
        if (mmmLogo != null) {
            //mmmLogo = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("menuTitle", mmmLogo, classConfig, mmmLogo.GetPosition());
            mmmLogo = MmgBmpScaler.ScaleMmgBmp(mmmLogo, 0.75f, true);
            MmgHelper.CenterHor(mmmLogo);
            mmmLogo.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(60));
            layerFront.Add(mmmLogo);
            mmmLogo.SetIsVisible(false);
        }
        
        file = "mmm_game_title.png";
        lval = MmgHelper.GetBasicCachedBmp(file);
        mmmLogoMini = lval;
        if (mmmLogoMini != null) {
            //mmmLogoMini = MmgHelper.ContainsKeyMmgBmpScaleAndPosition("menuTitleMini", mmmLogoMini, classConfig, mmmLogoMini.GetPosition());
            mmmLogoMini = MmgBmpScaler.ScaleMmgBmp(mmmLogoMini, 0.50f, true);
            MmgHelper.CenterHor(mmmLogoMini);
            mmmLogoMini.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(20));
            layerFront.Add(mmmLogoMini);
            mmmLogoMini.SetIsVisible(false);
        }        

        file = "Click on the desired board size:";
        boardSizePrompt = MmgFontData.CreateDefaultMmgFontLg();
        boardSizePrompt.SetText(file);
        boardSizePrompt.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(boardSizePrompt);
        boardSizePrompt.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(150));
        layerFront.Add(boardSizePrompt);
        boardSizePrompt.SetIsVisible(false);

        file = "Small";
        boardSizeSmall = MmgFontData.CreateDefaultMmgFontSm();
        boardSizeSmall.SetText(file);
        if (boardSizeSelectedIdx == 0) {
            boardSizeSmall.SetMmgColor(MmgColor.GetRed());
        } else {
            boardSizeSmall.SetMmgColor(MmgColor.GetWhite());
        }
        MmgHelper.CenterHor(boardSizeSmall);
        boardSizeSmall.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(175));
        boardSizeSmall.GetPosition().SetX(boardSizeSmall.GetPosition().GetX() - MmgHelper.ScaleValue(100));
        layerFront.Add(boardSizeSmall);
        boardSizeSmall.SetIsVisible(false);

        file = "Medium";
        boardSizeMedium = MmgFontData.CreateDefaultMmgFontSm();
        boardSizeMedium.SetText(file);
        if (boardSizeSelectedIdx == 1) {
            boardSizeMedium.SetMmgColor(MmgColor.GetRed());
        } else {
            boardSizeMedium.SetMmgColor(MmgColor.GetWhite());
        }
        MmgHelper.CenterHor(boardSizeMedium);
        boardSizeMedium.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(175));
        boardSizeMedium.GetPosition().SetX(boardSizeMedium.GetPosition().GetX() - MmgHelper.ScaleValue(0));
        layerFront.Add(boardSizeMedium);
        boardSizeMedium.SetIsVisible(false);

        file = "Large";
        boardSizeLarge = MmgFontData.CreateDefaultMmgFontSm();
        boardSizeLarge.SetText(file);
        if (boardSizeSelectedIdx == 2) {
            boardSizeLarge.SetMmgColor(MmgColor.GetRed());
        } else {
            boardSizeLarge.SetMmgColor(MmgColor.GetWhite());
        }
        MmgHelper.CenterHor(boardSizeLarge);
        boardSizeLarge.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(175));
        boardSizeLarge.GetPosition().SetX(boardSizeLarge.GetPosition().GetX() + MmgHelper.ScaleValue(100));
        layerFront.Add(boardSizeLarge);
        boardSizeLarge.SetIsVisible(false);

        file = "Instructions:";
        gameInstructionsPrompt = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameInstructionsPrompt.SetText(file);
        gameInstructionsPrompt.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(gameInstructionsPrompt);
        gameInstructionsPrompt.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(210));
        layerFront.Add(gameInstructionsPrompt);
        gameInstructionsPrompt.SetIsVisible(false);

        //Instructions
        //Player one is the blue player, left hand-side of the board. Player two, if active, 
        //is the green player, right hand-size of the board. Each player gets to flip two cards
        //on their turn. If that player finds a match then they can flip over two more cards. 
        //Memory Match tracks how many turns you've had and how many matches you've found.
        
        file = "Player one is the blue player, left hand-side of the board. Player two, if active,";
        gameInstructions1 = MmgFontData.CreateDefaultMmgFontSm();
        gameInstructions1.SetText(file);
        gameInstructions1.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(gameInstructions1);
        gameInstructions1.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(230));
        layerFront.Add(gameInstructions1);
        gameInstructions1.SetIsVisible(false);

        file = "is the green player, right hand-size of the board. Each player gets to flip two cards";
        gameInstructions2 = MmgFontData.CreateDefaultMmgFontSm();
        gameInstructions2.SetText(file);
        gameInstructions2.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(gameInstructions2);
        gameInstructions2.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(250));
        layerFront.Add(gameInstructions2);
        gameInstructions2.SetIsVisible(false);

        file = "on their turn. If that player finds a match then they can flip over two more cards.";
        gameInstructions3 = MmgFontData.CreateDefaultMmgFontSm();
        gameInstructions3.SetText(file);
        gameInstructions3.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(gameInstructions3);
        gameInstructions3.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(270));
        layerFront.Add(gameInstructions3);
        gameInstructions3.SetIsVisible(false);

        file = "Memory Match tracks how many turns you've had and how many matches you've found.";
        gameInstructions4 = MmgFontData.CreateDefaultMmgFontSm();
        gameInstructions4.SetText(file);
        gameInstructions4.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHor(gameInstructions4);
        gameInstructions4.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(290));
        layerFront.Add(gameInstructions4);
        gameInstructions4.SetIsVisible(false);

        file = "Press 'A' or click here to play!";
        gameStartPrompt = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameStartPrompt.SetText(file);
        gameStartPrompt.SetMmgColor(MmgColor.GetCalmBlue());
        MmgHelper.CenterHor(gameStartPrompt);
        gameStartPrompt.GetPosition().SetY(screenPos.GetY() + MmgHelper.ScaleValue(320));
        layerFront.Add(gameStartPrompt);
        gameStartPrompt.SetIsVisible(false);
    }

    /**
     *
     */
    private void InitPlayerData() {
        /////////////////////////////////////////////////////Set player data
        //Set player score
        scorePlayerLeft = 0;
        scorePlayerRight = 0;

        //Set player turn count
        turnCntPlayerLeft = 0;
        turnCntPlayerRight = 0;

        //Set player score text
        SetScoreLeftText(scorePlayerLeft);
        SetScoreRightText(scorePlayerRight);

        //Set player turn count text
        SetTurnsLeftText(turnCntPlayerLeft);        
        SetTurnsRightText(turnCntPlayerRight);
        
        currentPlayerMarkerIsRight = false;
        SetCurrentPlayerMarker(currentPlayerMarkerIsRight);
    }
    
    /**
     * 
     * 
     * @param isRight 
     */
    private void SetCurrentPlayerMarker(boolean isRight) {
        if(!isRight) {
            currentPlayerMarker.SetPosition(currentPlayerMarkerPosLeft);
        } else {
            currentPlayerMarker.SetPosition(currentPlayerMarkerPosRight);
        }
    }

    /**
     * 
     * @param ar 
     */
    private void InitTmpCardArray(int[] ar) {
        if(ar != null && ar.length > 0) {
            for(int i = 0; i < ar.length; i++) {
                ar[i] = -1;
            }
        }
    }
    
    /**
     * 
     */
    private void InitGameBoard() {
        /*
            cols x rows
        
            630x300@64x64
            9x4 = 36 = 18 pairs

            630x300@48x48
            13x6 = 78 = 39 pairs

            630x300@32x32
            18x8 = 144 = 72 pairs
        */
        
        layerCards.Clear();
        int[] tmp = null;
        int cnt = 0;
        int rows = 0;
        int cols = 0;
        int w = 0;
        int h = 0;
        int offsetX = 0;
        int offsetY = 0;
        String b = null;
        String f = null;
        MmgVector2 fo = null;
        MmgVector2 fbo = null;        
        
        if(boardSizeSelectedIdx == 0) {
            //small board
            scoreGameWin = 18;
            cnt = 36;
            rows = 4;
            cols = 9;
            w = 64;
            h = 64;
            offsetX = 100;
            offsetY = 175;
            b = "card_back_64x64.png";
            f = "card_front_64x64.png";
            fo = new MmgVector2(16, 16);
            fbo = new MmgVector2(0, 0);            
            
        } else if(boardSizeSelectedIdx == 1) {
            //medium
            scoreGameWin = 30;
            cnt = 60;
            rows = 5;
            cols = 12;
            w = 48;
            h = 48;
            offsetX = 70;
            offsetY = 178;            
            b = "card_back_48x48.png";
            f = "card_front_48x48.png";
            fo = new MmgVector2(6, 6);
            fbo = new MmgVector2(0, 0);            
            
        } else {
            //large
            scoreGameWin = 48;
            cnt = 96;
            rows = 6;
            cols = 16;
            w = 40;
            h = 40;
            offsetX = 30;
            offsetY = 175;
            b = "card_back_40x40.png";
            f = "card_front_40x40.png";
            fo = new MmgVector2(4, 4);
            fbo = new MmgVector2(0, 0);
        }

        tmp = new int[cnt];
        InitTmpCardArray(tmp);
        PopulateCardArray(tmp, cnt, scoreGameWin, rows, cols, w, h, offsetX, offsetY, b, f, fo, fbo);
    }
    
    /**
     * 
     * @param ar
     * @param len
     * @param matchCnt 
     */
    private void PopulateCardArray(int[] ar, int len, int matchCnt, int rows, int cols, int w, int h, int offsetX, int offsetY, String backStr, String frontBgroundStr, MmgVector2 offsetFrontImg, MmgVector2 offsetFrontBgroundImg) {
        int match = 0;
        for(int i = 0; i < matchCnt; i++) {
            int idx1 = rand.nextInt(len);
            while(ar[idx1] != -1) {
                idx1 = rand.nextInt(len);
            }
            ar[idx1] = idx1;
            
            int idx2 = rand.nextInt(len);
            while(ar[idx2] != -1) {
                idx2 = rand.nextInt(len);
            }            
            ar[idx2] = idx2;
            
            MemoryItem mItm1 = MemoryItemList.GetRandomMemoryItem().Clone();
            MemoryItem mItm2 = mItm1.Clone();

            mItm1.matchNum = match;
            mItm1.index = idx1;
            mItm1.matchIndex = idx2;
            mItm1.offsetFrontImgX = offsetFrontImg.GetX();
            mItm1.offsetFrontImgY = offsetFrontImg.GetY();
            
            mItm2.matchNum = match;
            mItm2.index = idx2;
            mItm2.matchIndex = idx1;
            mItm2.offsetFrontImgX = offsetFrontImg.GetX();
            mItm2.offsetFrontImgY = offsetFrontImg.GetY();
                        
            String file = null;
            MmgBmp lval = null;
            int posX = 0;
            int posY = 0;
            
            file = backStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm1.backImg = lval;
            if (lval != null) {
                posY = offsetY + ((int)(idx1 % rows) * (lval.GetHeight() + MmgHelper.ScaleValue(10)));
                posX = offsetX + ((int)(idx1 / rows) * (lval.GetWidth() + MmgHelper.ScaleValue(10)));
                mItm1.SetPosition(new MmgVector2(posX, posY));
            } else {
                MmgHelper.wr("Is NULL!");
            }
            
            MmgHelper.wr("Index: " + i + ", " + posX + "x" + posY);
            file = mItm1.frontStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm1.frontImg = lval;
            
            file = frontBgroundStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm1.frontBgroundImg = lval;
            layerCards.Add(mItm1);

            file = backStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm2.backImg = lval;
            if (lval != null) {
                posY = offsetY + ((int)(idx2 % rows) * (lval.GetHeight() + MmgHelper.ScaleValue(10)));
                posX = offsetX + ((int)(idx2 / rows) * (lval.GetWidth() + MmgHelper.ScaleValue(10)));
                mItm2.SetPosition(new MmgVector2(posX, posY));
            } else {
                MmgHelper.wr("Is NULL!");
            }
            
            MmgHelper.wr("Index: " + i + ", " + posX + "x" + posY);
            file = mItm2.frontStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm2.frontImg = lval;
            
            file = frontBgroundStr;
            lval = MmgHelper.GetBasicCachedBmp(file);
            mItm2.frontBgroundImg = lval;
            
            layerCards.Add(mItm2);                        
            match++;
        }
    }
    
    /**
     * Loads all the resources needed to display this game screen and support
     * all Screen states.
     */
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        rand = new Random((int) System.currentTimeMillis());
        classConfig = MmgHelper.ReadClassConfigFile(GameSettings.CLASS_CONFIG_DIR + GameSettings.NAME + "/screen_game.txt");

        //Set two player specific code here
        if (gameType == GameType.GAME_TWO_PLAYER) {

        }

        //Run initialization
        InitGameLayerContainers();
        InitScreenDimensionsAndPosition();
        InitMainBackgroundPopupDimensions();
        InitGameStateResourcesPopups();
        InitGameStateResourcesCountDown();
        InitGameGeneralResources();
        InitGameStateResourcesGameOver();
        InitGameStateResourcesStart();
        InitPlayerData();
        
        /*********************************************************
        ********* CHAPTER 7 CHALLENGE 1 SOLUTION PART 2 **********
        **********************************************************
        * Solution: 
        * Using similar code to that used to load any image in this class
        * you can load your new image into the game. Your code may differ slightly
        * but, you should have the name of the image to load and you need to set the
        * value of the class field.
        */
    
        myImg = MmgHelper.GetBasicCachedBmp("MyImg.jpg");
        if (myImg != null) {
            
            /*********************************************************
            ********* CHAPTER 7 CHALLENGE 1 SOLUTION PART 3 **********
            **********************************************************
            * Solution:
            * We were also asked to set the position of the image and add it to
            * a container for display on the game screen.
            */            
            
            myImg.SetPosition(10, bground.GetPosition().GetY() + myImg.GetHeight() + 10);
            layerFront.Add(myImg);
            myImg.SetIsVisible(false);            
        }        

        /////////////////////////////////////////////////////Set initial game state
        SetState(State.SHOW_GAME_START);
        ready = true;
        pause = false;
    }

    //Getters and Setters
    
    /**
     * Sets the current game type.
     *
     * @param gt The current game type.
     */
    public void SetGameType(GameType gt) {
        gameType = gt;
    }

    /**
     * Gets the current game type.
     *
     * @return The current game type.
     */
    public GameType GetGameType() {
        return gameType;
    }

    /**
     *
     * @return
     */
    public GameBoardSize GetGameBoardSize() {
        return gameBoardSize;
    }

    /**
     *
     * @param gs
     */
    public void SetGameBoardSize(GameBoardSize gs) {
        gameBoardSize = gs;
    }

    /**
     * Updates player2's score, left hand paddle.
     *
     * @param score The score to set for player two.
     */
    private void SetScoreLeftText(int score) {
        String tmp = score + "";
        if (tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreLeft.SetText("Matches: " + tmp);
    }

    /**
     * 
     * @param turns
     */
    private void SetTurnsLeftText(int turns) {
        String tmp = turns + "";
        if (tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        turnsLeft.SetText("Turns: " + tmp);
    }
    
    /**
     * Updates player1's score, right hand paddle.
     *
     * @param score The score to set for player one.
     */
    private void SetScoreRightText(int score) {
        String tmp = score + "";
        if (tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreRight.SetText("Matches: " + tmp);
    }
     
    /**
     * 
     * @param turns
     */
    private void SetTurnsRightText(int turns) {
        String tmp = turns + "";
        if (tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        turnsRight.SetText("Turns: " + tmp);
    }
    
    /**
     * Converts the given speed to a uniform speed per frame so that the game
     * movement will be the same even if the game runs at different frame rates.
     *
     * @param speed The target speed to convert to a speed per frame.
     *
     * @return A converted speed that represents the speed per frame of the
     * given input speed.
     */
    private static int GetSpeedPerFrame(int speed) {
        return (int) (speed / (MemoryMatch.FPS - 4));
    }

    //Input Handlers
    
    /**
     * A method to handle A button click events from the MainFrame class.
     *
     * @return A Boolean indicating if this Screen has handled the A click
     * event.
     */
    @Override
    public boolean ProcessAClick(int src) {
        if(state == State.SHOW_GAME_START) {
            SetState(State.SHOW_COUNT_DOWN);
            return true;
            
        } else if (state == State.SHOW_GAME_EXIT) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;

        } else if (state == State.SHOW_GAME_OVER) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;

        }

        return false;
    }

    /**
     * 
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean ProcessMouseClick(int x, int y, int btnIndex) {
        int nx;
        int ny;
        boolean b;
        
        if(state == State.SHOW_GAME_START) {
            nx = x;
            ny = y + (int)(gameStartPrompt.GetHeight() / 2);
            b = false;
            
            //Check for start game click
            b = MmgHelper.RectCollision(nx, ny, new MmgRect(gameStartPrompt.GetX(), gameStartPrompt.GetY(), (gameStartPrompt.GetY() + gameStartPrompt.GetHeight()), (gameStartPrompt.GetX() + gameStartPrompt.GetWidth())));
            if(b) {
                SetState(State.SHOW_COUNT_DOWN);
            }
            
            //Check for small board click
            b = MmgHelper.RectCollision(nx, ny, new MmgRect(boardSizeSmall.GetX(), boardSizeSmall.GetY(), (boardSizeSmall.GetY() + boardSizeSmall.GetHeight()), (gameStartPrompt.GetX() + gameStartPrompt.GetWidth())));
            if(b) {
                boardSizeSelectedIdx = 0;
                SetGameBoardSizeColor();
            }
            
            //Check for medium board click
            b = MmgHelper.RectCollision(nx, ny, new MmgRect(boardSizeMedium.GetX(), boardSizeMedium.GetY(), (boardSizeMedium.GetY() + boardSizeMedium.GetHeight()), (gameStartPrompt.GetX() + gameStartPrompt.GetWidth())));
            if(b) {
                boardSizeSelectedIdx = 1;
                SetGameBoardSizeColor();
            }
            
            //Check for large board click
            b = MmgHelper.RectCollision(nx, ny, new MmgRect(boardSizeLarge.GetX(), boardSizeLarge.GetY(), (boardSizeLarge.GetY() + boardSizeLarge.GetHeight()), (gameStartPrompt.GetX() + gameStartPrompt.GetWidth())));
            if(b) {
                boardSizeSelectedIdx = 2;
                SetGameBoardSizeColor();
            }
            
            return true;
            
        } else if(state == State.SHOW_GAME) {
            if(!clickFreeze) {
                nx = x;
                ny = y; 

                int len = layerCards.GetCount();
                MemoryItem itm;
                int prevScore = 0;
                
                if(currentPlayerMarkerIsRight) {
                    prevScore = scorePlayerRight;
                } else {
                    prevScore = scorePlayerLeft;
                }
                
                for(int i = 0; i < len; i++) {
                    itm = (MemoryItem)layerCards.GetAt(i);
                    int lx = itm.GetPosition().GetX();
                    int ly = itm.GetPosition().GetY();
                    int lw = itm.backImg.GetWidth();
                    int lh = itm.backImg.GetHeight();                

                    if(nx >= lx && nx <= lx + lw) {
                        if(ny >= ly && ny <= ly + lh) {
                            currentPlayerClicks++;
                            itm.Clicked();
                            if(currentPlayerClicks % 2 == 0) {
                                CheckForMatches(itm);
                            } else {
                                clickedCards.add(itm);
                            }
                        }
                    }
                }
                
                if(clickFreeze) {
                    if(gameType == GameType.GAME_TWO_PLAYER) {
                        if(currentPlayerMarkerIsRight) {
                            if(scorePlayerRight == prevScore) {
                                currentPlayerMarkerIsRight = !currentPlayerMarkerIsRight;
                                turnsPlayerRight++;
                            }
                        } else {
                            if(scorePlayerLeft == prevScore) {
                                currentPlayerMarkerIsRight = !currentPlayerMarkerIsRight;
                                turnsPlayerLeft++;
                            }
                        }

                        SetCurrentPlayerMarker(currentPlayerMarkerIsRight);
                        SetScoreLeftText(scorePlayerLeft);
                        SetScoreRightText(scorePlayerRight);
                        SetTurnsLeftText(turnsPlayerLeft);
                        SetTurnsRightText(turnsPlayerRight);
                    } else {
                        if(scorePlayerLeft == prevScore) {
                            turnsPlayerLeft++;
                        }

                        SetScoreLeftText(scorePlayerLeft);
                        SetTurnsLeftText(turnsPlayerLeft);
                    }
                }
            }
            
            return true;            
        } else {
            return false;
        }
    }
    
    /**
     * A method for checking if there has been a match in the clicked cards this turn.
     * 
     * @param itm  The last card clicked.
     */
    public void CheckForMatches(MemoryItem itm) {
        if(clickedCards != null) {
            int len = clickedCards.size();
            for(int i = 0; i < len; i++) {
                MemoryItem tItm = clickedCards.get(i);
                ProcessMatchCheck(tItm, itm);
            }
        }
    }
    
    /**
     * 
     * 
     * @param tItm
     * @param itm 
     */
    public void ProcessMatchCheck(MemoryItem tItm, MemoryItem itm) {
        if(tItm.index == itm.matchIndex || itm.frontStr.equals(tItm.frontStr)) {
            //match found
            //keep flipped
            //play match sound
            clickedCards.clear();
            if(currentPlayerMarkerIsRight) {
                scorePlayerRight++;
                greenCheckRight.SetIsVisible(true);
            } else {
                scorePlayerLeft++;
                greenCheckLeft.SetIsVisible(true);                        
            }
            showResultStart = MmgHelper.CtMs();

            if(scorePlayerLeft >= scoreGameWin || scorePlayerRight >= scoreGameWin) {
                SetState(State.SHOW_GAME_OVER);
            }
            clickFreeze = true;
        } else {
            //no match found
            //flip back
            currentPlayerClicks = 0;
            if(currentPlayerMarkerIsRight) {
                redXRight.SetIsVisible(true);
            } else {
                redXLeft.SetIsVisible(true);
            }
            showResultStart = MmgHelper.CtMs();

            clickedCards.add(itm);
            clickFreeze = true;
            //change player
            //play error sound
        }        
    }
    
    
    /**
     * A method to handle B button click events from the MainFrame class.
     *
     * @return A Boolean indicating if this Screen has handled the B click
     * event.
     */
    @Override
    public boolean ProcessBClick(int src) {
        if (state == State.SHOW_GAME_OVER) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;

        } else {
            if (state != State.SHOW_GAME_EXIT) {
                SetState(State.SHOW_GAME_EXIT);
                return true;

            } else {
                SetState(statePrev);
                return true;
            }
        }
    }

    /**
     * A method to handle debug click events from the MainFrame class, the D key
     * on the keyboard. You can use this method to turn on different debugging
     * helpers.
     */
    @Override
    public void ProcessDebugClick() {
        /*
        if(scoreGameWin > 1) {
            MmgHelper.wr("Setting game win score to 1.");
            scoreGameWin = 1;
        } else if(scoreGameWin == 1 && infiniteBounce == false) {
            MmgHelper.wr("Setting infinite bounce to true.");
            infiniteBounce = true;
        } else if(scoreGameWin == 1 && infiniteBounce == true) {
            MmgHelper.wr("Setting game win score to 7 and infinite bounce to false.");
            scoreGameWin = 7;
            infiniteBounce = false;
        }
         */
    }

    /**
     * A method to handle key press events from the MainFrame class.
     *
     * @param c The character of the key that was pressed on the keyboard.
     *
     * @return A Boolean indicating if the key press event was handled by this
     * Screen.
     */
    @Override
    public boolean ProcessKeyPress(char c, int code) {
        if (state == State.SHOW_GAME && pause == false) {
            if (gameType == GameType.GAME_TWO_PLAYER) {
                if (c == 'x' || c == 'X') {
                    return true;

                } else if (c == 's' || c == 'S') {
                    return true;

                }
            }
        }

        return false;
    }

    /**
     * A method to handle key release events from the MainFrame class.
     *
     * @param c The character of the key that was released on the keyboard.
     *
     * @return A Boolean indicating if the key release event was handled by this
     * Screen.
     */
    @Override
    public boolean ProcessKeyRelease(char c, int code) {
        if (state == State.SHOW_GAME && pause == false) {
            if (gameType == GameType.GAME_TWO_PLAYER) {
                if (c == 'x' || c == 'X') {
                    return true;

                } else if (c == 's' || c == 'S') {
                    return true;

                }
            }
        }

        return false;
    }

    /**
     * A method to handle D-pad press events from the MainFrame class.
     *
     * @param dir The D-pad code, UP, DOWN, LEFT, RIGHT of the direction that
     * was pressed on the keyboard.
     *
     * @return A Boolean indicating if the D-pad press was handled by this
     * Screen.
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        if (state == State.SHOW_GAME && pause == false) {
            if (dir == GameSettings.DOWN_KEYBOARD) {
                return true;

            } else if (dir == GameSettings.UP_KEYBOARD) {
                return true;

            } else if (this.gameType == GameType.GAME_TWO_PLAYER) {
                if (dir == GameSettings.DOWN_GAMEPAD_1 || dir == GameSettings.DOWN_GPIO) {
                    if (dir == GameSettings.DOWN_GPIO) {
                        MmgHelper.wr(("GPIO Gamepad Down Button Event"));
                    }
                    return true;

                } else if (dir == GameSettings.UP_GAMEPAD_1 || dir == GameSettings.UP_GPIO) {
                    if (dir == GameSettings.UP_GPIO) {
                        MmgHelper.wr(("GPIO Gamepad Up Button Event"));
                    }
                    return true;

                }
            }
        }

        return false;
    }

    /**
     * A method to handle D-pad release events from the MainFrame class.
     *
     * @param dir The D-pad code, UP, DOWN, LEFT, RIGHT of the direction that
     * was released on the keyboard.
     *
     * @return A Boolean indicating if the D-pad release was handled by this
     * Screen.
     */
    @Override
    public boolean ProcessDpadRelease(int dir) {
        if (state == State.SHOW_GAME && pause == false) {
            if (dir == GameSettings.DOWN_KEYBOARD) {
                return true;

            } else if (dir == GameSettings.UP_KEYBOARD) {
                return true;

            } else if (this.gameType == GameType.GAME_TWO_PLAYER) {
                if (dir == GameSettings.DOWN_GAMEPAD_1 || dir == GameSettings.DOWN_GPIO) {
                    return true;

                } else if (dir == GameSettings.UP_GAMEPAD_1 || dir == GameSettings.UP_GPIO) {
                    return true;

                }
            }
        }

        return false;
    }

    /**
     * Processes mouse movement for player 2's input, the left hand paddle.
     *
     * @param x The X position of the mouse with the Screen's offset taken into
     * account.
     * @param y The Y position of the mouse with the Screen's offset taken into
     * account.
     *
     * @return Returns a Boolean indicating if the event was consumed by this
     * game Screen.
     */
    //@Override
    //public boolean ProcessMouseMove(int x, int y) {
        //MmgHelper.wr("ProcessMouseClick: " + x + ", " + y);
        /*
        if (state == State.SHOW_GAME && pause == false) {
            if (gameType == GameType.GAME_TWO_PLAYER) {
                if (y >= screenPos.GetY() && y <= (screenPos.GetY() + GetHeight() - MmgHelper.ScaleValue(80))) {
                    lastX = x;
                    lastY = y;
                    mousePos = true;
                    return true;
                }
            }
        }

        mousePos = false;
        */
        //return false;
    //}

    /**
     * Resets certain aspects of the UI that are related to the actual game.
     * Some aspects of the UI are left visible during the in-game countdown.
     */
    private void ResetGame() {
        InitPlayerData();
        //lastX = 0;
        //lastY = 0;
        //mousePos = false;
        InitGameBoard();
    }

    /**
     * 
     */
    private void SetGameBoardSizeColor() {
        if(boardSizeSelectedIdx == 0) {
            boardSizeSmall.SetMmgColor(MmgColor.GetRed());
            boardSizeMedium.SetMmgColor(MmgColor.GetWhite());
            boardSizeLarge.SetMmgColor(MmgColor.GetWhite());
        } else if(boardSizeSelectedIdx == 1) {
            boardSizeSmall.SetMmgColor(MmgColor.GetWhite());
            boardSizeMedium.SetMmgColor(MmgColor.GetRed());
            boardSizeLarge.SetMmgColor(MmgColor.GetWhite());            
        } else {
            boardSizeSmall.SetMmgColor(MmgColor.GetWhite());
            boardSizeMedium.SetMmgColor(MmgColor.GetWhite());
            boardSizeLarge.SetMmgColor(MmgColor.GetRed());            
        }
    }
    
    
    /**
     * Sets the Screen's current state. The state is used to prepare what
     * MmgObj's are visible for the given state.
     *
     * @param inS The desired State to set the Screen to.
     */
    private void SetState(State inS) {
        MmgHelper.wr("SetState: " + statePrev + ", " + state + ", " + inS);
        statePrev = state;
        
        /*********************************************************
        ********* CHAPTER 7 CHALLENGE 1 SOLUTION PART 4 **********
        **********************************************************
        * Solution:
        * There are two changes that need to be made in this method.
        * We want the new image to display on the SHOW_GAME game state.
        * To do this correctly we need to add code to hide the image and
        * to show it accordingly. Follow the example already in place for
        * this game state.
        */        
        
        //clean up prev state
        switch (statePrev) {
            case NONE:
                break;

            case SHOW_GAME_START:
                bgroundMainPopup.SetIsVisible(false);
                mmmLogo.SetIsVisible(false);
                
                boardSizePrompt.SetIsVisible(false);
                boardSizeSmall.SetIsVisible(false);
                boardSizeMedium.SetIsVisible(false);
                boardSizeLarge.SetIsVisible(false);                
                
                gameInstructionsPrompt.SetIsVisible(false);
                gameInstructions1.SetIsVisible(false);
                gameInstructions2.SetIsVisible(false);
                gameInstructions3.SetIsVisible(false);
                gameInstructions4.SetIsVisible(false);
                gameStartPrompt.SetIsVisible(false);
                exit.SetIsVisible(false);
                exitBground.SetIsVisible(false);
                exit.SetPosition(exitPosGameBground);
                exitBground.SetPosition(exitBgroundPosGameBground);
                break;

            case SHOW_GAME:
                mmmLogoMini.SetIsVisible(false);
                scoreLeftTitle.SetIsVisible(false);
                scoreLeft.SetIsVisible(false);
                turnsLeft.SetIsVisible(false);
                scoreRightTitle.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                turnsLeft.SetIsVisible(false);              
                exit.SetIsVisible(false);
                exitBground.SetIsVisible(false);
                currentPlayerMarker.SetIsVisible(false);
                layerCards.SetIsVisible(false);
                myImg.SetIsVisible(false);
                break;

            case SHOW_COUNT_DOWN:
                bgroundMainPopup.SetIsVisible(false);
                mmmLogo.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                txtGoal.SetIsVisible(false);
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);
                exit.SetIsVisible(false);
                exitBground.SetIsVisible(false);
                exit.SetPosition(exitPosGameBground);
                exitBground.SetPosition(exitBgroundPosGameBground);
                break;

            case SHOW_GAME_OVER:
                bgroundMainPopup.SetIsVisible(false);
                mmmLogo.SetIsVisible(false);
                txtGameOver1.SetIsVisible(false);
                txtGameOver2.SetIsVisible(false);                
                break;

            case SHOW_GAME_EXIT:
                bgroundMainPopup.SetIsVisible(false);                
                mmmLogo.SetIsVisible(false);                
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);                
                break;
        }

        state = inS;

        switch (state) {
            case NONE:
                ResetGame();

                bgroundMainPopup.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                
                scoreLeftTitle.SetIsVisible(false);
                scoreLeft.SetIsVisible(false);
                turnsLeft.SetIsVisible(false);
                scoreRightTitle.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                turnsLeft.SetIsVisible(false);
                currentPlayerMarker.SetIsVisible(false);
                
                exit.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                txtGoal.SetIsVisible(false);
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);
                txtGameOver1.SetIsVisible(false);
                txtGameOver2.SetIsVisible(false);
                        
                mmmLogo.SetIsVisible(false);
                mmmLogoMini.SetIsVisible(false);
                boardSizePrompt.SetIsVisible(false);
                boardSizeSmall.SetIsVisible(false);
                boardSizeMedium.SetIsVisible(false);
                boardSizeLarge.SetIsVisible(false);
                gameInstructionsPrompt.SetIsVisible(false);
                gameInstructions1.SetIsVisible(false);
                gameInstructions2.SetIsVisible(false);
                gameInstructions3.SetIsVisible(false);
                gameInstructions4.SetIsVisible(false);
                gameStartPrompt.SetIsVisible(false);                

                pause = false;
                isDirty = false;
                break;

            case SHOW_GAME_START:      
                bground.SetIsVisible(true);
                bgroundMainPopup.SetIsVisible(true);
                mmmLogo.SetIsVisible(true);
                boardSizePrompt.SetIsVisible(true);
                boardSizeSmall.SetIsVisible(true);
                boardSizeMedium.SetIsVisible(true);
                boardSizeLarge.SetIsVisible(true);
                gameInstructionsPrompt.SetIsVisible(true);
                gameInstructions1.SetIsVisible(true);
                gameInstructions2.SetIsVisible(true);
                gameInstructions3.SetIsVisible(true);
                gameInstructions4.SetIsVisible(true);
                gameStartPrompt.SetIsVisible(true);
                exitBground.SetIsVisible(true);
                exit.SetIsVisible(true);
                exitBground.SetPosition(exitBgroundPosPopupBground);                
                exit.SetPosition(exitPosPopupBground);
                SetGameBoardSizeColor();
                break;

            case SHOW_GAME_OVER:
                bground.SetIsVisible(false);
                mmmLogo.SetIsVisible(true);
                bgroundMainPopup.SetIsVisible(true);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                txtGoal.SetIsVisible(false);
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);
                scoreLeft.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                exit.SetIsVisible(true);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                
                if (scorePlayerRight == scoreGameWin) {
                    txtGameOver1.SetIsVisible(false);
                    txtGameOver2.SetIsVisible(true);
                } else if (scorePlayerLeft == scoreGameWin) {
                    txtGameOver1.SetIsVisible(true);
                    txtGameOver2.SetIsVisible(false);
                }
                
                numberState = NumberState.NONE;
                pause = false;
                isDirty = true;
                break;

            case SHOW_GAME:
                mmmLogoMini.SetIsVisible(true);
                scoreLeftTitle.SetIsVisible(true);
                scoreLeft.SetIsVisible(true);
                turnsLeft.SetIsVisible(true);
                
                if(gameType == GameType.GAME_TWO_PLAYER) {
                    currentPlayerMarker.SetIsVisible(true);    
                    scoreRightTitle.SetIsVisible(true);
                    scoreRight.SetIsVisible(true);
                    turnsRight.SetIsVisible(true);                    
                }                
                
                exit.SetIsVisible(true);
                exitBground.SetIsVisible(true);
                layerCards.SetIsVisible(true);
                pause = false;
                isDirty = true;
                myImg.SetIsVisible(true);                
                break;

            case SHOW_COUNT_DOWN:
                bgroundMainPopup.SetIsVisible(true);
                mmmLogo.SetIsVisible(true);                
                exit.SetIsVisible(true);
                exitBground.SetIsVisible(true);
                txtGoal.SetIsVisible(true);
                txtDirecP1.SetIsVisible(true);

                if(gameType == GameType.GAME_TWO_PLAYER) {
                    txtDirecP2.SetIsVisible(true);
                }
                
                numberState = NumberState.NONE;
                pause = false;
                isDirty = true;
                break;

            case SHOW_GAME_EXIT:
                bgroundMainPopup.SetIsVisible(true);
                mmmLogo.SetIsVisible(true);                
                txtOk.SetIsVisible(true);
                txtCancel.SetIsVisible(true);                
                isDirty = true;
                break;
        }
    }

    /**
     * 
     */
    private void StartGame() {
        timeNumberMs = System.currentTimeMillis();
        ResetGame();
        SetState(State.SHOW_GAME);
    }
    
    /**
     * The DrawScreen method gets called by the MmgUpdate method if the Screen
     * is not paused and is responsible for drawing the current screen state.
     */
    @Override
    public void DrawScreen() {
        //ran each game frame
        pause = true;

        switch (state) {
            case NONE:
                break;

            case SHOW_GAME_START:
                break;

            case SHOW_GAME_EXIT:
                break;

            case SHOW_COUNT_DOWN_IN_GAME:
            case SHOW_COUNT_DOWN:
                // <editor-fold>
                switch (numberState) {
                    case NONE:
                        // <editor-fold>                        
                        timeNumberMs = System.currentTimeMillis();
                        numberState = NumberState.NUMBER_3;
                        number1.SetIsVisible(false);
                        number2.SetIsVisible(false);
                        number3.SetIsVisible(true);
                        if (state == State.SHOW_COUNT_DOWN) {
                            txtGoal.SetIsVisible(true);
                            txtDirecP1.SetIsVisible(true);
                            if (gameType == GameType.GAME_TWO_PLAYER) {
                                txtDirecP2.SetIsVisible(true);
                            } else {
                                txtDirecP2.SetIsVisible(false);
                            }
                        } else {
                            txtGoal.SetIsVisible(false);
                            txtDirecP1.SetIsVisible(false);
                            txtDirecP2.SetIsVisible(false);
                        }
                        break;
                    // </editor-fold>                        

                    case NUMBER_1:
                        // <editor-fold>
                        timeTmpMs = System.currentTimeMillis();
                        if (timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NONE;
                            number1.SetIsVisible(false);
                            number2.SetIsVisible(false);
                            number3.SetIsVisible(false);
                            txtDirecP1.SetIsVisible(false);
                            txtDirecP2.SetIsVisible(false);
                            StartGame();
                            //SetState(State.SHOW_GAME);
                            //bounceNorm.Play();                            
                        }
                        break;
                    // </editor-fold>

                    case NUMBER_2:
                        // <editor-fold>
                        timeTmpMs = System.currentTimeMillis();
                        if (timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NUMBER_1;
                            number1.SetIsVisible(true);
                            number2.SetIsVisible(false);
                            number3.SetIsVisible(false);
                            txtDirecP1.SetIsVisible(true);
                            if (state == State.SHOW_COUNT_DOWN) {
                                txtGoal.SetIsVisible(true);
                                if (gameType == GameType.GAME_TWO_PLAYER) {
                                    txtDirecP2.SetIsVisible(true);
                                } else {
                                    txtDirecP2.SetIsVisible(false);
                                }
                            } else {
                                txtGoal.SetIsVisible(false);
                                txtDirecP1.SetIsVisible(false);
                                txtDirecP2.SetIsVisible(false);
                            }
                            //bounceNorm.Play();                            
                        }
                        break;
                    // </editor-fold>

                    case NUMBER_3:
                        // <editor-fold>
                        timeTmpMs = System.currentTimeMillis();
                        if (timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NUMBER_2;
                            number1.SetIsVisible(false);
                            number2.SetIsVisible(true);
                            number3.SetIsVisible(false);
                            if (state == State.SHOW_COUNT_DOWN) {
                                txtGoal.SetIsVisible(true);
                                if (gameType == GameType.GAME_TWO_PLAYER) {
                                    txtDirecP2.SetIsVisible(true);
                                } else {
                                    txtDirecP2.SetIsVisible(false);
                                }
                            } else {
                                txtGoal.SetIsVisible(false);
                                txtDirecP1.SetIsVisible(false);
                                txtDirecP2.SetIsVisible(false);
                            }
                            //bounceNorm.Play();
                        }
                        break;
                    // </editor-fold>

                }
                // </editor-fold>
                break;

            case SHOW_GAME:
                // <editor-fold>
                //player two movement
                if (gameType == GameType.GAME_TWO_PLAYER) {
                    // <editor-fold>

                    // </editor-fold>
                } else {
                    //AI
                    // <editor-fold>

                    // </editor-fold>
                }

                //player one movement
                break;
            // </editor-fold>
        }

        pause = false;
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    @Override
    public void UnloadResources() {
        pause = true;
        SetBackground(null);

        ResetGame();
        state = State.NONE;
        statePrev = State.NONE;
        bground = null;

        number1 = null;
        number2 = null;
        number3 = null;

        scoreLeft = null;
        scoreRight = null;
        exit = null;
        exitBground = null;

        rand = null;
        screenPos = null;
        bgroundPopupSrc = null;

        txtOk = null;
        txtCancel = null;
        txtGoal = null;
        
        txtDirecP1 = null;
        txtDirecP2 = null;
        txtGameOver1 = null;
        txtGameOver2 = null;
        classConfig = null;

        bgroundMainPopup = null;
        boardSizeLarge = null;
        boardSizeMedium = null;
        boardSizePrompt = null;
        boardSizeSmall = null;

        gameInstructions1 = null;
        gameInstructions2 = null;
        gameInstructions3 = null;
        gameInstructions4 = null;

        gameInstructionsPrompt = null;
        gameStartPrompt = null;
        mmmLogo = null;
        
        layerBack = null;
        layerFront = null;
        layerMiddle = null;
        bgroundMainPopup = null;
        
        /*********************************************************
        ********* CHAPTER 7 CHALLENGE 1 SOLUTION PART 5 **********
        **********************************************************
        * Solution:
        * The last thing we were asked to do is to clean up after ourselves.
        * Setting the class field to null should suffice.
        */
        
        myImg = null;
        
        super.UnloadResources();

        ClearObjs();
        ready = false;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && isVisible == true) {
            super.GetObjects().MmgDraw(p);

            //clear errors
            if(redXLeft.GetIsVisible() || redXRight.GetIsVisible() || greenCheckLeft.GetIsVisible() || greenCheckRight.GetIsVisible()) {
                if(MmgHelper.CtMs() - showResultStart >= showResultDuration) {
                    if(gameType == GameType.GAME_TWO_PLAYER) {
                        if(currentPlayerMarkerIsRight) {
                            redXLeft.SetIsVisible(false);
                            greenCheckLeft.SetIsVisible(false);                        
                        } else {
                            redXRight.SetIsVisible(false);
                            greenCheckRight.SetIsVisible(false);                            
                        }
                    } else {
                        if(currentPlayerMarkerIsRight) {
                            redXRight.SetIsVisible(false);
                            greenCheckRight.SetIsVisible(false);
                        } else {
                            redXLeft.SetIsVisible(false);
                            greenCheckLeft.SetIsVisible(false);                        
                        }
                    }

                    int len = clickedCards.size();
                    for(int i = 0; i < len; i++) {
                        clickedCards.get(i).flipped = false;
                    }
                    clickedCards.clear();
                    clickFreeze = false;
                }
            }
        }
    }
}
