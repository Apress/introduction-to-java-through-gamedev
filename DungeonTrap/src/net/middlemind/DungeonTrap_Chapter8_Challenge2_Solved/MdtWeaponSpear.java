package net.middlemind.DungeonTrap_Chapter8_Challenge2_Solved;

import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;

/**
 * A class that represents a weapon of type spear.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 09/19/2020
 */
public class MdtWeaponSpear extends MdtWeapon {


    /*********************************************************
    ****************** CHAPTER 8 CHALLENGE 1 *****************
    **********************************************************
    * Description: 
    * Find the package, net.middlemind.DungeonTrap_Chapter8_Challenge1, and open the MdtWeaponSpear.java file.
    * You've been tasked with completing some work on the Dungeon Trap game. The task you've been given is to 
    * explore adding a flame spear to the game as the default weapon for both players. The graphics for the new
    * weapon asset can be found in the game project's image resources directory:
    * 
    * ./cfg/drawable/DungeonTrap
    * 
    * You also must plug the new weapon into the game. Since there is already a spear weapon in the game the
    * lead programmer has asked you to use inheritance to create the new java class. Keep everything the same
    * except the images used and make the new weapon more powerful and slightly slower. Make sure the weapon is
    * the default weapon for both players. This will require changing the MdtCharInter.java class, the super class
    * for player and enemy characters in the game.
    * 
    * Clue:
    * If you get stuck take a close look at how the MdtWeaponSpear.java class is used in the MdtCharInter.java
    * class. If you change the images and attack properties of the default spear you can make it look and act like
    * a flame spear. Think about that. Then think about inheritance in Java and using copy and paste to create a quick
    * copy of an existing class. One last hint, the default weapon for players and enemies is set in the constructor of the 
    * MdtCharInter.java class.
    * 
    * Search for "CHAPTER 8 CHALLENGE 1 SOLUTION" in the MdtWeaponSpearFlame.java file to find all the solution text.
    */    
        
    /**
     * A basic constructor that allows you to set the weapon holder and weapon type via constructor arguments.
     * 
     * @param Holder        The holder of the weapon.
     * @param WeaponType    The type of weapon held.
     * @param Player        The type of player that is holding the weapon.
     */
    public MdtWeaponSpear(MdtChar Holder, MdtWeaponType WeaponType, MdtPlayerType Player) {
        super(Holder, WeaponType, Player);
        subjFront = MmgHelper.GetBasicCachedBmp("weapon_spear_dir_front.png");
        subjFront = MmgBmpScaler.ScaleMmgBmp(subjFront, 2.0f, true);
        
        subjBack = MmgHelper.GetBasicCachedBmp("weapon_spear_dir_back.png");        
        subjBack = MmgBmpScaler.ScaleMmgBmp(subjBack, 2.0f, true);
        
        subjLeft = MmgHelper.GetBasicCachedBmp("weapon_spear_dir_left.png");
        subjLeft = MmgBmpScaler.ScaleMmgBmp(subjLeft, 2.0f, true);
        
        subjRight = MmgHelper.GetBasicCachedBmp("weapon_spear_dir_right.png");
        subjRight = MmgBmpScaler.ScaleMmgBmp(subjRight, 2.0f, true);
        
        SetMdtType(MdtObjType.WEAPON);
        SetMdtSubType(MdtObjSubType.WEAPON_SPEAR);
        SetAttackType(MdtWeaponAttackType.STABBING);
        SetWidth(subjBack.GetHeight());
        SetHeight(subjBack.GetHeight());
        SetDamage(1);
        SetAnimTimeMsTotal(250);
    }
    
    /**
     * Creates a clone of this object instance.
     * 
     * @return  A new instance of this class.
     */
    @Override
    public MdtWeaponSpear Clone() {
        MdtWeaponSpear ret = new MdtWeaponSpear(holder, weaponType, player);
        ret.SetAnimPrctComplete(GetAnimPrctComplete());
        ret.SetIsActive(GetIsActive());
        ret.SetAnimTimeMsCurrent(GetAnimTimeMsCurrent());
        ret.SetAnimTimeMsTotal(GetAnimTimeMsTotal());
        ret.SetAttackType(GetAttackType());
        
        if(GetMmgColor() == null) {
            ret.SetMmgColor(GetMmgColor());
        } else {
            ret.SetMmgColor(GetMmgColor().Clone());            
        }
        
        if(GetCurrent() == null) {
            ret.SetCurrent(GetCurrent());
        } else {
            ret.SetCurrent(GetCurrent().Clone());
        }
        
        ret.SetDamage(GetDamage());
        ret.SetHeight(GetHeight());
        ret.SetHasParent(GetHasParent());
        ret.SetIsVisible(GetIsVisible());
        ret.SetId(GetId());
        ret.SetHolder(GetHolder());
        ret.SetParent(GetParent());

        if(GetPosition() == null) {
            ret.SetPosition(GetPosition());
        } else {
            ret.SetPosition(GetPosition().Clone());
        }
        
        if(subjBack == null) {
            ret.subjBack = subjBack;
        } else {
            ret.subjBack = subjBack.CloneTyped();
        }

        if(subjFront == null) {
            ret.subjFront = subjFront;
        } else {
            ret.subjFront = subjFront.CloneTyped();
        }

        if(subjLeft == null) {
            ret.subjLeft = subjLeft;
        } else {
            ret.subjLeft = subjLeft.CloneTyped();
        }

        if(subjRight == null) {
            ret.subjRight = subjRight;
        } else {
            ret.subjRight = subjRight.CloneTyped();
        }        
        
        ret.throwingDir = throwingDir;
        ret.throwingFrame = throwingFrame;
        ret.throwingPath = throwingPath;
        ret.throwingSpeed = throwingSpeed;
        ret.throwingSpeedSkew = throwingSpeedSkew;
        ret.throwingCoolDown = throwingCoolDown;
        ret.throwingTimeMsRotation = throwingTimeMsRotation;
        ret.throwingTimeMsCurrent = throwingTimeMsCurrent;
        ret.screen = screen;
        ret.stabbingCoolDown = stabbingCoolDown;
        return ret;
    }
}