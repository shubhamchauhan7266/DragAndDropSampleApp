package com.ownwork.draganddropsampleapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DragAndDropActivity extends AppCompatActivity {

    private static final String IMAGEVIEW_TAG = "icon bitmap";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        initDragAndDrop();
    }

    private void initDragAndDrop() {
        final ImageView imageView = findViewById(R.id.image_view);

// Sets the bitmap for the ImageView from an icon bit map (defined elsewhere)
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.smile));

// Sets the tag
        imageView.setTag(IMAGEVIEW_TAG);

// Sets a long click listener for the ImageView using an anonymous listener object that
// implements the OnLongClickListener interface
        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            // Defines the one method for the interface, which is called when the View is long-clicked
            @Override
            public boolean onLongClick(View v) {

                // Create a new ClipData.
                // This is done in two steps to provide clarity. The convenience method
                // ClipData.newPlainText() can create a plain text ClipData in one step.

                // Create a new ClipData.Item from the ImageView object's tag
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);

                // Starts the drag

                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );

                return true;
            }
        });

        MyDragEventListener dragListen = new MyDragEventListener(this);
        imageView.setOnDragListener(dragListen);

    }
}
