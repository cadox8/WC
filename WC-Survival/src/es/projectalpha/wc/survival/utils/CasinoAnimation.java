package es.projectalpha.wc.survival.utils;

import es.projectalpha.wc.survival.WCFichas;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CasinoAnimation {

    private WCFichas.Fichas fichas;

    public CasinoAnimation(WCFichas.Fichas fichas){
        this.fichas = fichas;
    }

    public ItemStack animateWin(){
        int rand = new Random().nextInt(12) + 1;
        int pro = fichas.getPro();
        int result = rand + pro;

        if (fichas == WCFichas.Fichas.FICHA_500){
            result = 20;
        }

        switch (result){
            case 1:
                return WCFichas.Fichas.FICHA_200.getItemStack();
            case 2:
                return WCFichas.Fichas.FICHA_50.getItemStack();
            case 3:
                return WCFichas.Fichas.BASURA_3.getItemStack();
            case 4:
                return WCFichas.Fichas.FICHA_1.getItemStack();
            case 5:
                return WCFichas.Fichas.FICHA_10.getItemStack();
            case 6:
                return WCFichas.Fichas.BASURA_2.getItemStack();
            case 7:
                return WCFichas.Fichas.FICHA_1.getItemStack();
            case 8:
                return WCFichas.Fichas.FICHA_100.getItemStack();
            case 9:
                return WCFichas.Fichas.FICHA_10.getItemStack();
            case 10:
                return WCFichas.Fichas.BASURA_1.getItemStack();
            case 11:
                return WCFichas.Fichas.FICHA_200.getItemStack();
            case 12:
                return WCFichas.Fichas.FICHA_10.getItemStack();
            case 13:
                return WCFichas.Fichas.BASURA_2.getItemStack();
            case 14:
                return WCFichas.Fichas.BASURA_3.getItemStack();
            case 15:
                return WCFichas.Fichas.FICHA_10.getItemStack();
            case 16:
                return WCFichas.Fichas.FICHA_100.getItemStack();
            case 17:
                return WCFichas.Fichas.BASURA_1.getItemStack();
            case 18:
                return WCFichas.Fichas.BASURA_3.getItemStack();
            case 19:
                return WCFichas.Fichas.FICHA_500.getItemStack();
            case 20:
                return WCFichas.Fichas.FICHA_500.getItemStack();
            default:
                return WCFichas.Fichas.BASURA_1.getItemStack();
        }
    }
}
