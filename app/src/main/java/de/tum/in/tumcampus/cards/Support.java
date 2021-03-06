package de.tum.in.tumcampus.cards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import de.tum.in.tumcampus.R;
import de.tum.in.tumcampus.auxiliary.Utils;
import de.tum.in.tumcampus.models.managers.CardManager;

/**
 * Card that describes how to dismiss a card
 */
public class Support extends Card {

    public Support(Context context) {
        super(context);
    }

    @Override
    public int getTyp() {
        return CardManager.CARD_SUPPORT;
    }

    @Override
    public View getCardView(final Context context, ViewGroup parent) {
        super.getCardView(context, parent);
        mCard = mInflater.inflate(R.layout.card_support, parent, false);

        //Add links to imageviews
        mCard.findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(context.getString(R.string.facebook_link)));
                context.startActivity(browserIntent);
            }

        });
        mCard.findViewById(R.id.github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(context.getString(R.string.github_link)));
                context.startActivity(browserIntent);
            }
        });

        return mCard;
    }

    @Override
    public void discard(Editor editor) {
        Utils.setSetting(mContext, CardManager.SHOW_SUPPORT, false);
    }

    @Override
    public boolean shouldShow(SharedPreferences p) {
        return Utils.getSettingBool(mContext, CardManager.SHOW_SUPPORT, true);
    }
}
