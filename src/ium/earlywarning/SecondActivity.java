package ium.earlywarning;

import ium.earlywarning.widget.GIFView;
import android.support.v7.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity
{
	public static String APERTO = "allaperto";
	@SuppressWarnings("unused")
	private static String LOG_TAG = SecondActivity.class.getName();
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);


		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		final ActionBar actionBar = getSupportActionBar();
		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		// Create a tab listener that is called when the user changes tabs.
		ActionBar.TabListener tabListener = new ActionBar.TabListener()
		{
			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
			{
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
			{
				// hide the given tab
			}

			@Override
			public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
			{
				// probably ignore this event
			}
		};

		/**
		 * Aggiunge i tabs:
		 */
		actionBar.addTab(actionBar.newTab().setText("Chiuso").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Aperto").setTabListener(tabListener));
		if (getIntent().getBooleanExtra(APERTO, false))
		{
			getSupportActionBar().setSelectedNavigationItem(1);

		}

		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				// When swiping between pages, select the
				// corresponding tab.
				getSupportActionBar().setSelectedNavigationItem(position);
			}
		});
	}


	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{

		public SectionsPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount()
		{
			// Show 3 total pages.
			return 2;
		}


	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber)
		{
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			final View rootView = inflater.inflate(R.layout.fragment_second, container, false);

			Bundle args = getArguments();
			final Integer[] gifIDs;

			switch (args.getInt(ARG_SECTION_NUMBER))
			{
				case 1:
					gifIDs = new Integer[] { R.drawable.sciencebitch,
					        R.drawable.lol1, R.drawable.lol2, R.drawable.lol3,
					        R.drawable.lol4 };
					break;
				case 2:
					gifIDs = new Integer[] { R.drawable.sciencebitch,
					        R.drawable.lol1 };
					break;
				default:
					gifIDs = new Integer[] { R.drawable.pic1, R.drawable.pic2 };
			}

			LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.thumb_linearlayout);

			OnClickListener gifOnClickListener = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// display the images selected

					GIFView gifView = (GIFView) rootView.findViewById(R.id.main_gif);
					gifView.setMovie(((GIFView) v).getMovie());
				}
			};

			OnClickListener textViewOnClickListener = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// display the images selected

					GIFView gifView = (GIFView) rootView.findViewById(R.id.main_gif);
					String tvValue = (String) ((TextView) v).getText();
					gifView.setMovieResource(gifIDs[Integer.parseInt(tvValue)]);
				}
			};

			int i = 0;
			for (int id : gifIDs)
			{
				TextView tv = (TextView) inflater.inflate(R.layout.list_numb_tv, null);
				tv.setText(i + "");
				i++;
				// GIFView gv = (GIFView) inflater.inflate(R.layout.thumb_gif,
				// null);
				// gv.setMovieResource(id);
				// gv.setOnClickListener(gifOnClickListener);
				tv.setOnClickListener(textViewOnClickListener);
				linearLayout.addView(tv);

				TextView spacer = new TextView(getActivity());
				spacer.setLayoutParams(new LayoutParams(4, 2));
				spacer.setText(" ");
				linearLayout.addView(spacer);

			}
			return rootView;
		}
	}

}
