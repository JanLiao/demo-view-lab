package com.cvte.cons;

import java.util.ArrayList;
import java.util.List;

import com.cvte.entity.AnalysisData;
import com.cvte.entity.CircleData;
import com.cvte.entity.Component;
import com.cvte.entity.LabelColor;
import com.cvte.entity.LineData;
import com.cvte.entity.TaskDir;
import com.cvte.util.InputStreamCacher;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;

import javafx.beans.value.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月21日 下午4:35:39 
*/
public class Constant {

	public static String User = "";
	//弃用
	public static Integer InitStyle = 0;
	
	// 0-shipan  1-shibei  3-amd  4-avg 5-mask 6-all
	public static int CurrTab = 0;
	
	//view falg
	public static CircleData ViewPanAvg = null;
	public static CircleData ViewBeiAvg = null;
	public static LineData ViewCenterAvg = null;
	
	//view check is over
	public static boolean OverFlag = false;
	
	//保存4tab canvas
	public static List<Canvas> TabCanvas = new ArrayList<Canvas>();
	
	//初始化颜色
	public static List<LabelColor> ColorList = new ArrayList<LabelColor>();
	
	//维护一个DirTask数据
	public static List<TaskDir> DirList = new ArrayList<TaskDir>();
	
	//分析融合
	public static AnalysisData AnalysisMix = new AnalysisData();
	
	//查看融合结果      可去除
	public static AnalysisData AnalysisView = new AnalysisData();
	
	//当前各component状态
	public static Component component = new Component(1, true, "");
	
	//保存当前右侧scrollpane  内所有canvas
	public static List<Canvas> canvasList = new ArrayList<Canvas>();
	
	//右侧CheckBox状态
	public static List<JFXCheckBox> CheckBoxList = new ArrayList<JFXCheckBox>();
	//左下角CheckBox
	public static List<JFXCheckBox> LeftList = new ArrayList<JFXCheckBox>();
	
	//左侧radio
	public static JFXRadioButton radio = null;
	public static JFXRadioButton maskradio = null;
	public static JFXRadioButton avgradio = null;
	public static double SlideValue = 50;
	public static double beiSlideValue = 50;
	
	
	//保存3tab  avg
	public static CircleData panAvg = null;
	public static CircleData beiAvg = null;
	public static LineData centerAvg = null;
	
	//标准 覆盖label List
	public static List<Label> LabelList = new ArrayList<Label>();
	
	//Listener List监听
	public static List<ChangeListener<Boolean>> ListenerList = new ArrayList<ChangeListener<Boolean>>();
	public static List<ChangeListener<Boolean>> ListenerLeftList = new ArrayList<ChangeListener<Boolean>>();
	
	// slide 监听
	public static ChangeListener<Number> SlideListener = null;
	
	//保存shipan shibei center all 所选用户     弃用
//	public static String panUser = "";
//	public static String beiUser = "";
//	public static String centerUser = "";
//	public static String allLabelUser = "";
	
	public static Stage stage = new Stage();
	
	public static List<Label> CoverList = new ArrayList<Label>();
	
	public static Label fenzi = new Label("");
	public static Label fenmu = new Label("");
	public static JFXSlider slider = new JFXSlider();
	
	public static InputStreamCacher cacher;
	public static Image CurrImageLoad;
	public static Image PreImageLoad;
	public static Image NextImageLoad;
	
	public static String tmpImagePath = "";
	public static String tmpDataPath = "";
	
	public static Stage TmpStage = null;
	public static Label NameLabel = null;
	public static Label FlagLabel = null;
	public static Label NumLabel = null;
	public static List<Label> InnerLabel = new ArrayList<Label>();
	public static SingleSelectionModel<Tab> selectionModel = null;
	public static JFXComboBox<Label> jfxCombo = null;
	public static JFXButton BtnPre = null;
	public static JFXButton BtnSave = null;
	public static JFXButton BtnNext = null;
}
