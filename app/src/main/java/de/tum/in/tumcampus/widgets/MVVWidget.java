package de.tum.in.tumcampus.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.Calendar;

import de.tum.in.tumcampus.R;
import de.tum.in.tumcampus.auxiliary.Utils;
import de.tum.in.tumcampus.services.MVVWidgetService;

public class MVVWidget extends AppWidgetProvider {

    private RemoteViews rv;
    private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";
    AppWidgetManager appWidgetManager;
    int[] widgetIDs = null;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        widgetIDs = appWidgetIds;
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        this.appWidgetManager = appWidgetManager;
        Utils.log("WidgetMVV onupdate");
        for (int i = 0; i < N; i++) {
            Intent intent = new Intent(context, MVVWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            rv = new RemoteViews(context.getPackageName(), R.layout.mvv_widget);

            Utils.log("WidgetMVV create header");
            // set the header for the Widget layout
            Calendar c = Calendar.getInstance();
            String headerMVVWidget = "MVV Recent Searches " + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH)+ "-" + c.get(Calendar.YEAR);
            rv.setTextViewText(R.id.mvv_widget_header, headerMVVWidget);

            Utils.log("WidgetMVV setadapter");
            // set the adapter for the list view in the mensaWidget
            rv.setRemoteAdapter(appWidgetIds[i], R.id.mvv_widget_item, intent);
            rv.setEmptyView(R.id.empty_view, R.id.empty_view);

            rv.setOnClickPendingIntent(R.id.mvv_refresh, getPendingSelfIntent(context, SYNC_CLICKED));

            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);

        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (SYNC_CLICKED.equals(intent.getAction())) {
            if (!MVVWidgetService.loadRecentData())
                Utils.showToast(context, "No recent searches to show");
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


}

