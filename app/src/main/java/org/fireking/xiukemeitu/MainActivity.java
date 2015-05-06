package org.fireking.xiukemeitu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import org.fireking.xiukemeitu.ui.ClothFragment;
import org.fireking.xiukemeitu.ui.HotFragment;
import org.fireking.xiukemeitu.ui.RecomFragment;
import org.fireking.xiukemeitu.ui.SettingFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {

        // create and set the header
        View view = LayoutInflater.from(this).inflate(R.layout.custom_drawer, null);
        setDrawerHeaderCustom(view);

        // create sections
        this.addSection(newSection(getResources().getString(R.string.nav_recom), R.mipmap.ic_mic_white_24dp, new RecomFragment()).setSectionColor(getResources().getColor(R.color.color_03a9f4)));
        this.addSection(newSection(getResources().getString(R.string.nav_cloth), R.mipmap.ic_mic_white_24dp, new ClothFragment()).setSectionColor(getResources().getColor(R.color.color_9227b0)));
        this.addSection(newSection(getResources().getString(R.string.nav_hot), R.mipmap.ic_mic_white_24dp, new HotFragment()).setSectionColor(getResources().getColor(R.color.color_03a9f4)));
        this.addSection(newSection(getResources().getString(R.string.nav_setting), R.mipmap.ic_mic_white_24dp, new SettingFragment()).setSectionColor(getResources().getColor(R.color.color_03a9f4)));
    }
}
