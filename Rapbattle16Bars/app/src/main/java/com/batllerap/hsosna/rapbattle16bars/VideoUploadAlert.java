package com.batllerap.hsosna.rapbattle16bars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Controller.VideoUploadController;
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        super.onCreateDialog(savedInstanceState);
        beatID = getArguments().getInt("BeatID");
        battleID =getArguments().getInt("BattleID");
        fileFormat = getArguments().getString("FileFormat");
        video =(File) getArguments().getSerializable("Video");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Video hochladen?")
                .setPositiveButton("Ja!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {

                            Toast.makeText(getContext(), "Upload gestartet....", Toast.LENGTH_LONG).show();
                            VideoUploadRequest  request= new VideoUploadRequest(beatID,battleID, video, fileFormat);
                            VideoUploadController up = new VideoUploadController();
                            up.execute(request);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

  /*  public void gibDaten(int beatID, int battleID,String FileFormat, File video){
        this.beatID =beatID;
        this.battleID =battleID;
        this.FileFormat= FileFormat;
        this.video =video;
    }*/

    public static VideoUploadAlert newInstance(int beatID, int battleID, String fileFormat, File video) {
        VideoUploadAlert f = new VideoUploadAlert();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("BeatID", beatID);
        args.putInt("BattleID", battleID);
        args.putString("FileFormat", fileFormat);
        args.putSerializable("Video",video);
        f.setArguments(args);

        return f;
    }
}