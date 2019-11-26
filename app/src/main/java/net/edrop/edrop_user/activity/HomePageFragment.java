package net.edrop.edrop_user.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import net.edrop.edrop_user.MyApplication;
import net.edrop.edrop_user.R;
import net.edrop.edrop_user.entity.ImageInfo;
import net.edrop.edrop_user.utils.ImageCarousel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment implements View.OnClickListener{
    private Activity activity;
    private View view;
    //搜索框控件
    private SearchView searchView;
    private AutoCompleteTextView mAutoCompleteTextView;//搜索输入框
    private ImageView mDeleteButton;//搜索框中的删除按钮
    // 图片轮播控件
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;
    private LinearLayout mLineLayoutDot;
    private ImageCarousel imageCarousel;
    private List<View> dots;//小点
    // 图片数据，包括图片标题、图片链接、数据、点击要打开的网站（点击打开的网页或一些提示指令）
    private List<ImageInfo> imageInfoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView();
        initData();
        initEvent();
        setListener();
        imageStart();
        return view;
    }

    @Nullable
    @Override
    //在Fragment中直接使用getContext方法容易产生空指针异常，覆写getContext方法
    public Context getContext() {
        activity = getActivity();
        if (activity == null) {
            return MyApplication.getInstance();
        }
        return activity;
    }

    private void initView(){
        //搜索框
        searchView=view.findViewById(R.id.view_search);
        mAutoCompleteTextView=searchView.findViewById(R.id.search_src_text);
        mDeleteButton=searchView.findViewById(R.id.search_close_btn);
        //轮播图
        mViewPager =view.findViewById(R.id.viewPager);
        mTvPagerTitle = view.findViewById(R.id.tv_pager_title);
        mLineLayoutDot = view.findViewById(R.id.lineLayout_dot);

        Fresco.initialize(getContext());
    }

    private void initData(){
        searchView.setIconifiedByDefault(false);//设置搜索图标是否显示在搜索框内
        //1:回车
        //2:前往
        //3:搜索
        //4:发送
        //5:下一項
        //6:完成
        searchView.setImeOptions(2);//设置输入法搜索选项字段，默认是搜索，可以是：下一页、发送、完成等
//        mSearchView.setInputType(1);//设置输入类型
//        mSearchView.setMaxWidth(200);//设置最大宽度
        searchView.setQueryHint("搜索");//设置查询提示字符串
//        mSearchView.setSubmitButtonEnabled(true);//设置是否显示搜索框展开时的提交按钮
        //设置SearchView下划线透明
        setUnderLinetransparent(searchView);
    }

    private void setListener(){
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("=query=",query);
                searchView.setQuery("", false);
                searchView.clearFocus();//收起键盘
                searchView.onActionViewCollapsed();//收起SearchView视图
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("=====newText=",newText);
                return false;
            }

        });
    }

    /**设置SearchView下划线透明**/
    private void setUnderLinetransparent(SearchView searchView){
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    /*---------------------------------------------设置图片轮播-------------------------------------------------*/
    //初始化事件
    private void initEvent() {
        imageInfoList = new ArrayList<>();
        imageInfoList.add(new ImageInfo(1, "图片1，公告1啦啦啦啦", "", "http://img0.imgtn.bdimg.com/it/u=1323060543,1759230141&fm=26&gp=0.jpg", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "图片2，公告2啦啦啦啦", "", "http://f.3dxy.com/Manual/pic/2014/0918/12/1409181250_47667.jpg", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "图片3，公告3啦啦啦啦", "", "http://img0.imgtn.bdimg.com/it/u=1323060543,1759230141&fm=26&gp=0.jpg", "http://www.cnblogs.com/luhuan/"));
        imageInfoList.add(new ImageInfo(1, "图片4，公告4啦啦啦啦", "仅展示", "http://f.3dxy.com/Manual/pic/2014/0918/12/1409181250_47667.jpg", ""));
        imageInfoList.add(new ImageInfo(1, "图片5，公告5啦啦啦啦", "仅展示", "http://img0.imgtn.bdimg.com/it/u=1323060543,1759230141&fm=26&gp=0.jpg", ""));
    }

    private void imageStart() {
        int[] imgaeIds = new int[]{R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4, R.id.pager_image5, R.id.pager_image6, R.id.pager_image7, R.id.pager_image8};
        String[] titles = new String[imageInfoList.size()];
        List<SimpleDraweeView> simpleDraweeViewList = new ArrayList<>();

        for (int i = 0; i < imageInfoList.size(); i++) {
            titles[i] = imageInfoList.get(i).getTitle();
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
            simpleDraweeView.setAspectRatio(1.78f);
            // 设置一张默认的图片
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(this.getResources())
                    .setPlaceholderImage(ContextCompat.getDrawable(getContext(),
                            R.drawable.defult),
                            ScalingUtils.ScaleType.CENTER_CROP)
                    .build();
            simpleDraweeView.setHierarchy(hierarchy);
            simpleDraweeView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

            //加载高分辨率图片;
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageInfoList.get(i).getImage()))
                    .setResizeOptions(new ResizeOptions(1280, 720))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
//                    .setLowResImageRequest(ImageRequest.fromUri(Uri.parse(listItemBean.test_pic_low))) //在加载高分辨率图片之前加载低分辨率图片
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);

            simpleDraweeView.setId(imgaeIds[i]);//给view设置id
            simpleDraweeView.setTag(imageInfoList.get(i));
            simpleDraweeView.setOnClickListener(this);
            titles[i] = imageInfoList.get(i).getTitle();
            simpleDraweeViewList.add(simpleDraweeView);

        }

        dots = addDots(mLineLayoutDot,
                fromResToDrawable(getContext(), R.drawable.ic_dot_focused),
                simpleDraweeViewList.size());
        imageCarousel = new ImageCarousel(getContext(), mViewPager, mTvPagerTitle, dots, 5000);
        imageCarousel.init(simpleDraweeViewList, titles).startAutoPlay();
        imageCarousel.start();
    }

    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return 小点的Id
     */
    private int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(getContext());
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            dot.setBackground(backgount);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dot.setId(View.generateViewId());
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.addView(dot);
            }
        });

        return dot.getId();
    }

    /**
     * 资源图片转Drawable
     *
     * @param context 上下文
     * @param resId   资源ID
     * @return 返回Drawable图像
     */
    public static Drawable fromResToDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
        //return context.getResources().getDrawable(resId);
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout 线性横向布局
     * @param backgount    小点资源图标
     * @param number       数量
     * @return 返回小点View集合
     */
    private List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout, backgount);
            dots.add(view.findViewById(dotId));
        }
        return dots;
    }
}