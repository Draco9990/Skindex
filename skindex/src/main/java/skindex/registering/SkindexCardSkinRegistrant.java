package skindex.registering;

import skindex.bundles.Bundle;
import skindex.skins.cards.CardSkin;

import java.util.ArrayList;
import java.util.List;

public interface SkindexCardSkinRegistrant extends ISkindexSubscriber{
    List<CardSkin> getCardSkinsToRegister();
}
