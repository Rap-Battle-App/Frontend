package com.batllerap.hsosna.rapbattle16bars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.batllerap.hsosna.rapbattle16bars.Controller.BattleController;
import com.batllerap.hsosna.rapbattle16bars.Controller.ConnectionController;
import com.batllerap.hsosna.rapbattle16bars.Controller.UploadVideoController;
import com.batllerap.hsosna.rapbattle16bars.Controller.VideoUploadController;
import com.batllerap.hsosna.rapbattle16bars.Model.request.VideoUploadRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
    private VideoUploadRequest  request;

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
                            System.out.println("BEAT ID:" + beatID);
                            System.out.println("Battle ID:" + battleID);
                            request= new VideoUploadRequest(beatID,battleID, video, fileFormat,ConnectionController.getContext(),ConnectionController.getCookieManager());
                            VideoUploadController up = new VideoUploadController(getContext(), request);
                            up.execute(video);


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