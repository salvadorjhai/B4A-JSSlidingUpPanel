package com.salvadorjhai.widgets;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import android.view.View;
import android.view.ViewGroup;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ActivityObject;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;


/**
 * This library provides a simple way to add a draggable sliding up panel (popularized by Google Music and Google Maps) to your Android application. Brought to you by Umano. 
 * Wrapped: https://github.com/umano/AndroidSlidingUpPanel
 * Known Issue: Using Anchor in designer will hide main and slideup views. use SetLayout via code instead of anchor
 * Sample:
 */
@ShortName("JSSlidingUpPanel")
@Version(0.3F)
@Author("salvadorjhai")
@DependsOn(values = {"JSSlidingUpPanel.aar", "android-support-v4"})
@ActivityObject
public class JSSlidingUpPanel extends ViewWrapper<SlidingUpPanelLayout> implements DesignerCustomView {
	
    /** The expanded. */
    public static int PANEL_STATE_EXPANDED = 0;
	
    /** The collapsed. */
    public static int PANEL_STATE_COLLAPSED = 1;
	
    /** The anchored. */
    public static int PANEL_STATE_ANCHORED = 2;
	
    /** The hidden. */
    public static int PANEL_STATE_HIDDEN = 3; 
	
    /** The dragging. */
    public static int PANEL_STATE_DRAGGING = 4;
	
    
	/** The m BA. */
	private BA mBA = null;
	
	/** The m event name. */
	private String mEventName = "";
	
	/** The main view. */
	private ViewGroup mainView = null;
	
	/** The main view H. */
	private int mainView_H = BALayout.LayoutParams.MATCH_PARENT;
	
	/** The main view W. */
	private int mainView_W = BALayout.LayoutParams.MATCH_PARENT;
	
	/** The slide up view. */
	private ViewGroup slideUpView = null;
	
	/** The slide up view H. */
	private int slideUpView_H = BALayout.LayoutParams.MATCH_PARENT;
	
	/** The slide up view W. */
	private int slideUpView_W = BALayout.LayoutParams.WRAP_CONTENT;
	
	/* (non-Javadoc)
	 * @see anywheresoftware.b4a.objects.ViewWrapper#Initialize(anywheresoftware.b4a.BA, java.lang.String)
	 */
	@Override
	public void Initialize(BA ba, String eventName) {
		_initialize(ba, null, eventName);
	}
  
	/* (non-Javadoc)
	 * @see anywheresoftware.b4a.keywords.Common.DesignerCustomView#_initialize(anywheresoftware.b4a.BA, java.lang.Object, java.lang.String)
	 */
	@Override
	@BA.Hide
	public void _initialize(BA ba, Object paramObject, String eventName) {
		innerInitialize(ba, eventName, false);
	}
	
	/* (non-Javadoc)
	 * @see anywheresoftware.b4a.objects.ViewWrapper#innerInitialize(anywheresoftware.b4a.BA, java.lang.String, boolean)
	 */
	@Override
	@BA.Hide
	public void innerInitialize(final BA ba, final String eventName, boolean keepOldObject) {
		this.mBA = ba;
		this.mEventName = eventName;
		
		try {
			// set object and props
			if (!keepOldObject) {
				this.setObject(new SlidingUpPanelLayout(ba.context));
			}
			
			// Inner Initialize
		    super.innerInitialize(ba, eventName, true);

			// Set event Listeners
			if (eventName.length() != 0) {
//				this.getObjectOrNull().setOnDragListener(new OnDragListener() {					
//					@Override
//					public boolean onDrag(View v, DragEvent event) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//				});				
			}
			
		} catch (Exception e) {
			BA.LogError(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see anywheresoftware.b4a.keywords.Common.DesignerCustomView#DesignerCreateView(anywheresoftware.b4a.objects.PanelWrapper, anywheresoftware.b4a.objects.LabelWrapper, anywheresoftware.b4a.objects.collections.Map)
	 */
	@Override
	public void DesignerCreateView(PanelWrapper paramPanelWrapper, LabelWrapper paramLabelWrapper, Map paramMap) {
		CustomViewWrapper.replaceBaseWithView2(paramPanelWrapper, this.getObjectOrNull());
	}	
	
	/**
	 * Sets the main view.
	 *
	 * @param view the view
	 * @param Width the width
	 * @param Height the height
	 * @return the JS sliding up panel
	 */
	public JSSlidingUpPanel setMainView(ViewGroup view, int Width, int Height) {
		mainView = view;
		mainView_H = Height;
		mainView_W = Width;
		return this;
	}
	
	/**
	 * Sets the slide up view.
	 *
	 * @param view the view
	 * @param Width the width
	 * @param Height the height
	 * @return the JS sliding up panel
	 */
	public JSSlidingUpPanel setSlideUpView(ViewGroup view, int Width, int Height) {
		slideUpView = view;
		slideUpView_H = Height;
		slideUpView_W = Width;
		return this;
	}
	
	/**
	 * Builds the.
	 */
	public void Build() {
		if (mainView == null || slideUpView == null) {
			BA.LogError("You forgot to call 'setMainView' and 'setSlideUpView'");
			return;
		}
		this.getObjectOrNull().addView(mainView, mainView_W, mainView_H);
		this.getObjectOrNull().addView(slideUpView, slideUpView_W, slideUpView_H);
	}


    /**
     * Gets the anchor point.
     *
     * @return the anchor point
     */
    public float getAnchorPoint() {
           return this.getObjectOrNull().getAnchorPoint();
    }

    /**
     * Gets the covered fade color.
     *
     * @return the covered fade color
     */
    public int getCoveredFadeColor() {
           return this.getObjectOrNull().getCoveredFadeColor();
    }

    /**
     * Gets the current parallax offset.
     *
     * @return the current parallax offset
     */
    public int getCurrentParallaxOffset() {
           return this.getObjectOrNull().getCurrentParallaxOffset();
    }

    /**
     * Gets the min fling velocity.
     *
     * @return the min fling velocity
     */
    public int getMinFlingVelocity() {
           return this.getObjectOrNull().getMinFlingVelocity();
    }

    /**
     * Gets the panel height.
     *
     * @return the panel height
     */
    public int getPanelHeight() {
           return this.getObjectOrNull().getPanelHeight();
    }

    /**
     * Gets the shadow height.
     *
     * @return the shadow height
     */
    public int getShadowHeight() {
           return this.getObjectOrNull().getShadowHeight();
    }

    /**
     * Checks if is clip panel.
     *
     * @return true, if is clip panel
     */
    public boolean isClipPanel() {
           return this.getObjectOrNull().isClipPanel();
    }

    /**
     * Checks if is overlayed.
     *
     * @return true, if is overlayed
     */
    public boolean isOverlayed() {
           return this.getObjectOrNull().isOverlayed();
    }

    /**
     * Checks if is touch enabled.
     *
     * @return true, if is touch enabled
     */
    public boolean isTouchEnabled() {
           return this.getObjectOrNull().isTouchEnabled();
    }

    /**
     * Sets the anchor point.
     *
     * @param anchorPoint the anchor point
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setAnchorPoint(float anchorPoint) {
           this.getObjectOrNull().setAnchorPoint(anchorPoint);
           return this;
    }

    /**
     * Sets the clip panel.
     *
     * @param clip the clip
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setClipPanel(boolean clip) {
           this.getObjectOrNull().setClipPanel(clip);
           return this;
    }

    /**
     * Sets the covered fade color.
     *
     * @param color the color
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setCoveredFadeColor(int color) {
           this.getObjectOrNull().setCoveredFadeColor(color);
           return this;
    }

    /**
     * Sets the drag view.
     *
     * @param dragView the drag view
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setDragView(View dragView) {
           this.getObjectOrNull().setDragView(dragView);
           return this;
    }

    /**
     * Sets the gravity.
     *
     * @param gravity the gravity
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setGravity(int gravity) {
           this.getObjectOrNull().setGravity(gravity);
           return this;
    }

    /**
     * Sets the min fling velocity.
     *
     * @param val the val
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setMinFlingVelocity(int val) {
           this.getObjectOrNull().setMinFlingVelocity(val);
           return this;
    }

    /**
     * Sets the overlayed.
     *
     * @param overlayed the overlayed
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setOverlayed(boolean overlayed) {
           this.getObjectOrNull().setOverlayed(overlayed);
           return this;
    }

    /**
     * Sets the panel height.
     *
     * @param val the val
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setPanelHeight(int val) {
           this.getObjectOrNull().setPanelHeight(val);
           return this;
    }

    /**
     * Sets the parallax offset.
     *
     * @param val the val
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setParallaxOffset(int val) {
           this.getObjectOrNull().setParallaxOffset(val);
           return this;
    }

    /**
     * Sets the scrollable view.
     *
     * @param scrollableView the scrollable view
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setScrollableView(View scrollableView) {
           this.getObjectOrNull().setScrollableView(scrollableView);
           return this;
    }

    /**
     * Sets the shadow height.
     *
     * @param val the val
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setShadowHeight(int val) {
           this.getObjectOrNull().setShadowHeight(val);
           return this;
    }

    /**
     * Sets the touch enabled.
     *
     * @param enabled the enabled
     * @return the JS sliding up panel
     */
    public JSSlidingUpPanel setTouchEnabled(boolean enabled) {
           this.getObjectOrNull().setTouchEnabled(enabled);
           return this;
    }

	/**
	 * Gets the panel state.
	 *
	 * @return the panel state
	 */
	public int getPanelState() {
		return PanelState.fromPanelState(this.getObjectOrNull().getPanelState());
	}

	/**
	 * Sets the panel state.
	 *
	 * @param panelState the panel state
	 * @return the JS sliding up panel
	 */
	public JSSlidingUpPanel setPanelState(int panelState) {
		this.getObjectOrNull().setPanelState(PanelState.toPanelState(panelState));
		return this;
	}

	/**
	 * The Class PanelState.
	 */
	@ShortName("JSPanelState")
    public static class PanelState {

    	/**
	     * To panel state.
	     *
	     * @param state the state
	     * @return the com.sothree.slidinguppanel. sliding up panel layout. panel state
	     */
	    @Hide
    	public static com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState toPanelState(int state) {
    		switch (state) {
    		case 1: 
    			return com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.COLLAPSED;
    		case 2: 
    			return com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.ANCHORED;
    		case 3: 
    			return com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.HIDDEN;
    		case 4: 
    			return com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.DRAGGING;
    		default:
    			return com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState.EXPANDED;    			
    		}
    	}
    	
    	/**
	     * From panel state.
	     *
	     * @param state the state
	     * @return the int
	     */
	    @Hide
    	public static int fromPanelState(com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState state) {
    		switch (state) {
    		case COLLAPSED: 
    			return PANEL_STATE_COLLAPSED;
    		case ANCHORED: 
    			return PANEL_STATE_ANCHORED;
    		case HIDDEN: 
    			return PANEL_STATE_HIDDEN;
    		case DRAGGING: 
    			return PANEL_STATE_DRAGGING;
    		default:
    			return PANEL_STATE_EXPANDED;    			
    		}
    	}
    	
    	
    }

	
}
