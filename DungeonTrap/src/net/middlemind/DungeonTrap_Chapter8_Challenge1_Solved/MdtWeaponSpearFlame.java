package net.middlemind.DungeonTrap_Chapter8_Challenge1_Solved;

import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;

/**
 * A class that represents a weapon of type spear.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 09/19/2020
 */
public class MdtWeaponSpearFlame extends MdtWeaponSpear {

    /*********************************************************
    ********* CHAPTER 8 CHALLENGE 1 SOLUTION PART 1 **********
    **********************************************************
    * Solution:
    * The first part of the solution is to create a copy of the 
    * MdtWeaponSpear.java file and rename to something the resembles
    * a flame spear.
    */    
    
    /**
     * A basic constructor that allows you to set the weapon holder and weapon type via constructor arguments.
     * 
     * @param Holder        The holder of the weapon.
     * @param WeaponType    The type of weapon held.
     * @param Player        The type of player that is holding the weapon.
     */
    public MdtWeaponSpearFlame(MdtChar Holder, MdtWeaponType WeaponType, MdtPlayerType Player) {
        super(Holder, WeaponType, Player);
        
        /*********************************************************
        ********* CHAPTER 8 CHALLENGE 1 SOLUTION PART 2 **********
        **********************************************************
        * Solution:
        * The next step in the solution requires us to change the images
        * that are used to draw the spear. Because all of this is part of
        * the super class we only need to adjust the resources used to set
        * the weapon images for up, down, left, and right facing spear attacks.
        */          
        
        subjFront = MmgHelper.GetBasicCachedBmp("weapon_spear_flame_dir_front.png");
        subjFront = MmgBmpScaler.ScaleMmgBmp(subjFront, 2.0f, true);
        
        subjBack = MmgHelper.GetBasicCachedBmp("weapon_spear_flame_dir_back.png");        
        subjBack = MmgBmpScaler.ScaleMmgBmp(subjBack, 2.0f, true);
        
        subjLeft = MmgHelper.GetBasicCachedBmp("weapon_spear_flame_dir_left.png");
        subjLeft = MmgBmpScaler.ScaleMmgBmp(subjLeft, 2.0f, true);
        
        subjRight = MmgHelper.GetBasicCachedBmp("weapon_spear_flame_dir_right.png");
        subjRight = MmgBmpScaler.ScaleMmgBmp(subjRight, 2.0f, true);
        
        SetMdtType(MdtObjType.WEAPON);
        SetMdtSubType(MdtObjSubType.WEAPON_SPEAR);
        SetAttackType(MdtWeaponAttackType.STABBING);
        SetWidth(subjBack.GetHeight());
        SetHeight(subjBack.GetHeight());
        
        /*********************************************************
        ********* CHAPTER 8 CHALLENGE 1 SOLUTION PART 3 **********
        **********************************************************
        * Solution:
        * We were also asked to make the spear more powerful and slightly slower.
        * I chose an attack strength of 2, up from 1, and a weapon time of 350,
        * up from 250.
        */ 
        
        SetDamage(2);
        SetAnimTimeMsTotal(350);
    }
    
    /**
     * Creates a clone of this object instance.
     * 
     * @return  A new instance of this class.
     */
    @Override
    public MdtWeaponSpearFlame Clone() {
        MdtWeaponSpearFlame ret = new MdtWeaponSpearFlame(holder, weaponType, player);
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