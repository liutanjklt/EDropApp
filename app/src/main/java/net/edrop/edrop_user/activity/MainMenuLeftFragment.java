package net.edrop.edrop_user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.edrop.edrop_user.R;

public class MainMenuLeftFragment extends Fragment {
	private View myView;
	private TextView myMoney;
	private TextView myAddress;
	private TextView myOrder;
	private TextView inviteFriends;
	private TextView businessCooperation;
	private TextView aboutEDrop;
	private TextView setting;
	private TextView feedback;
	private MyListener myListener;

	//重写
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.fragment_home_left_menu, container, false);
		return myView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//初始化控件以及设置
		initView();
	}

	@Override
	public void onResume() {
		super.onResume();
		//初始化监听事件
		initEvent();
	}

	/**初始化控件*/
	private void initView(){
        myMoney=getActivity().findViewById(R.id.myMoney);
        myAddress=getActivity().findViewById(R.id.myAddress);
        myOrder=getActivity().findViewById(R.id.myOrder);
        inviteFriends=getActivity().findViewById(R.id.inviteFriends);
        businessCooperation=getActivity().findViewById(R.id.businessCooperation);
        aboutEDrop=getActivity().findViewById(R.id.aboutEDrop);
        setting=getActivity().findViewById(R.id.setting);
        feedback=getActivity().findViewById(R.id.feedback);
	}

	/**初始化默认数据【这个需要在activity中执行，原因是：在布局文件中通过<fragment>的方式引用Fragment，打开Activity的时候，Fragment的生命周期函数均执行了】
	 * 那么就存在一个问题，初始化fragment中的数据，可能会在activity数据初始化之前执行
	 */
	public void setDefaultDatas(){
		//修改首页左边数据
        myListener=new MyListener();
        myMoney.setOnClickListener(myListener);
        myAddress.setOnClickListener(myListener);
        myOrder.setOnClickListener(myListener);
        inviteFriends.setOnClickListener(myListener);
        businessCooperation.setOnClickListener(myListener);
        aboutEDrop.setOnClickListener(myListener);
        setting.setOnClickListener(myListener);
        feedback.setOnClickListener(myListener);
	}

	/**初始化监听事件*/
	private void initEvent(){

	}
	private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myMoney:
                    Toast.makeText(getActivity(),myMoney.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.myAddress:
                    Toast.makeText(getActivity(),myAddress.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.myOrder:
                    Toast.makeText(getActivity(),myOrder.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.inviteFriends:
                    Toast.makeText(getActivity(),inviteFriends.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.businessCooperation:
                    Toast.makeText(getActivity(),businessCooperation.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.aboutEDrop:
                    Toast.makeText(getActivity(),aboutEDrop.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.setting:
                    Toast.makeText(getActivity(),setting.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.feedback:
                    Toast.makeText(getActivity(),feedback.getText().toString(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}