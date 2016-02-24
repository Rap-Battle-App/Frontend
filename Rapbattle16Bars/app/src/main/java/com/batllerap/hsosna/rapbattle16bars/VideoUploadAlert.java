package com.batllerap.hsosna.rapbattle16bars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Controller.ConnectionController;
import com.batllerap.hsosna.rapbattle16bars.Controller.VideoUploadController;

import com.batllerap.hsosna.rapbattle16bars.Model.Battle.OpenBattle;
import com.batllerap.hsosna.rapbattle16bars.Model.profile2.User;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;

import java.io.File;
import java.io.IOException;

/**
 * Created by Albert on 15.01.2016.
 */
public class VideoUploadAlert extends DialogFragment {
    private int beatID;
    private int battleID;
    private String fileFormat;
    private File video;
    private VideoUploadRequest request;
    private User aktUser;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        super.onCreateDialog(savedInstanceState);
        beatID = getArguments().getInt("BeatID");
        battleID = getArguments().getInt("BattleID");
        fileFormat = getArguments().getString("FileFormat");
        video = (File) getArguments().getSerializable("Video");
        aktUser = (User) getArguments().getSerializable("User");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Video hochladen?")
                .setPositiveButton("Ja!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            System.out.println("BEAT ID:" + beatID);
                            System.out.println("Battle ID:" + battleID);
                            request = new VideoUploadRequest(beatID, battleID, video, fileFormat, ConnectionController.getContext(), ConnectionController.getCookieManager());
                            VideoUploadController up = new VideoUploadController(getContext(), request, aktUser);
                            up.execute(video);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), OpenBattleActivity.class);
                        intent.putExtra("User", aktUser);
                        OpenBattle battle = null;
                        try {
                            battle = BattleController.getOpenBattle(battleID);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        intent.putExtra("Battle", battle);
                        startActivity(intent);
                    }
                })
        .setNeutralButton("Video ansehen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                intent.putExtra("url", video.getAbsolutePath());
                intent.putExtra("beatID", beatID);
                intent.putExtra("battleID", battleID);
                intent.putExtra("video", video);
                intent.putExtra("User", aktUser);
                startActivity(intent);
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }


    public static VideoUploadAlert newInstance(int beatID, int battleID, String fileFormat, File video, User aktUser) {
        VideoUploadAlert f = new VideoUploadAlert();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("BeatID", beatID);
        args.putInt("BattleID", battleID);
        args.putString("FileFormat", fileFormat);
        args.putSerializable("Video", video);
        args.putSerializable("User", aktUser);
        f.setArguments(args);
        return f;
    }

}