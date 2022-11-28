package net.middlemind.MemoryMatch;

import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;

/**
 * A class that represents one card on the memory match game board.
 * 
 * @author Victor G. Brusca, Middlemind Games 07-20-2022 8:13 EST
 */
public class MemoryItem extends MmgObj {
    
    /**
     * A string value representing the image resource name to be used for the back of a memory match game card.
     */
    public String backStr;
    
    /**
     * A string value representing the image resource name to be used for the front of a memory match game card.
     */
    public String frontStr;
    
    /**
     * 
     */
    public String frontBgroundStr;    
    
    /**
     * A string value representing the name of the image on the front of a memory match game card.
     */
    public String nameStr;
    
    /**
     * The actual image resource to be used when drawing the back of a game card.
     */
    public MmgBmp backImg = null;
    
    /**
     * The actual image resource to be used when drawing the front of a game card.
     */
    public MmgBmp frontImg = null;
    
    /**
     * 
     */
    public MmgBmp frontBgroundImg = null;
    
    /**
     * A font text representation of the game card's name.
     */
    public MmgFont nameFnt = null;
    
    /**
     * A Boolean indicating if this game card has been flipped yet.
     */
    public boolean shown = false;
    
    /**
     * A Boolean indicating if this game card has been matched yet.
     */
    public boolean matched = false;
    
    /**
     * An integer indicating what match the match involved with this game card was. Set when a match is made.
     */
    public int matchNum = -1;
    
    /**
     * 
     */
    public boolean flipped = false;
    
    /**
     * 
     */
    public int index;
    
    /**
     * 
     */
    public int matchIndex;
    
    /**
     * 
     */
    public int offsetFrontImgBgroundX;
    
    /**
     * 
     */
    public int offsetFrontImgBgroundY;    
    
    /**
     * 
     */
    public int offsetFrontImgX;
    
    /**
     * 
     */
    public int offsetFrontImgY;    
    
    /**
     * 
     */
    public MemoryItem() {
        
    }
    
    /**
     * 
     * 
     * @param Front
     * @param Back
     * @param Name 
     */
    public MemoryItem(String Front, String Back, String Name) {
        frontStr = Front;
        backStr = Back;
        nameStr = Name;
    }
    
    /**
     * 
     * @param Front
     * @param Back
     * @param FrontBack
     * @param Name 
     */
    public MemoryItem(String Front, String Back, String FrontBack, String Name) {
        frontStr = Front;
        backStr = Back;
        frontBgroundStr = FrontBack;
        nameStr = Name;
    }    
    
    /**
     * 
     * @param Front
     * @param Back
     * @param Name
     * @param FrontImg
     * @param BackImg 
     */
    public MemoryItem(String Front, String Back, String Name, MmgBmp FrontImg, MmgBmp BackImg) {
        frontStr = Front;
        backStr = Back;
        nameStr = Name;
        frontImg = FrontImg;
        backImg = BackImg;
    }
    
    /**
     * 
     * @param Front
     * @param Back
     * @param FrontBack
     * @param Name
     * @param FrontImg
     * @param BackImg
     * @param FrontBackImg 
     */
    public MemoryItem(String Front, String Back, String FrontBack, String Name, MmgBmp FrontImg, MmgBmp BackImg, MmgBmp FrontBackImg) {
        frontStr = Front;
        frontBgroundStr = FrontBack;
        backStr = Back;
        nameStr = Name;
        frontImg = FrontImg;
        frontBgroundImg = FrontBackImg;
        backImg = BackImg;
    }        
    
    /**
     * 
     * @param mItm
     * @return 
     */
    public static MemoryItem Clone(MemoryItem mItm) {
        MemoryItem newItm = new MemoryItem();
        newItm.backImg = mItm.backImg;
        newItm.backStr = mItm.backStr;
        newItm.frontImg = mItm.frontImg;
        newItm.frontStr = mItm.frontStr;
        newItm.nameFnt = mItm.nameFnt;
        newItm.nameStr = mItm.nameStr;
        newItm.frontBgroundImg = mItm.frontBgroundImg;        
        newItm.frontBgroundStr = mItm.frontBgroundStr;
        return newItm;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public MemoryItem Clone() {
        MemoryItem newItm = new MemoryItem();
        newItm.backImg = backImg;
        newItm.backStr = backStr;
        newItm.frontImg = frontImg;
        newItm.frontStr = frontStr;
        newItm.nameFnt = nameFnt;
        newItm.nameStr = nameStr;
        newItm.frontBgroundImg = frontBgroundImg;        
        newItm.frontBgroundStr = frontBgroundStr;        
        return newItm;
    }    
    
    /**
     * The base drawing method for the bitmap object.
     *
     * @param p     The MmgPen used to draw this bitmap.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            if(flipped) {
                p.DrawBmp(frontBgroundImg, GetX() + offsetFrontImgBgroundX, GetY() + offsetFrontImgBgroundY);                
                p.DrawBmp(frontImg, GetX() + offsetFrontImgX, GetY() + offsetFrontImgY);
            } else {
                p.DrawBmp(backImg, GetX(), GetY());
            }
        }
    }

    /**
     * A method to handle when a memory item gets clicked.
     */
    public void Clicked() {
        if(!flipped) {
            flipped = true;
        }
        
        if(!shown) {
            shown = true;
        }
    }
}
