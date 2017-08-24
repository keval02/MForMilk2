package nivida.com.mformilk;



import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.wang.avi.AVLoadingIndicatorView;

public class Custom_ProgressDialog extends ProgressDialog {
	Context context;
	Animation myRotation;
	String comment;
	ImageView la;
	AVLoadingIndicatorView loadingIndicatorView;

	public Custom_ProgressDialog(Context context, String comment) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.comment = comment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressdialog);
		getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);

		loadingIndicatorView=(AVLoadingIndicatorView)findViewById(R.id.avi);



//		la = (ImageView) findViewById(R.id.img);
//
//		//final ImageView imageView = (ImageView) findViewById(R.id.imageView);
//		GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(la);
//		Glide.with(context).load(R.drawable.gif).into(imageViewTarget);
//
//		// la.setBackgroundResource(R.drawable.loading_spinner_icon);
//
//		//myRotation = AnimationUtils.loadAnimation(context, R.anim.rotate);

	}

	@Override
	public void show() {
		super.show();

	}

	@Override
	public void dismiss() {
		super.dismiss();
		//myRotation.cancel();
	}
}
