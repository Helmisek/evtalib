package cz.helmisek.evtademo.activity;

import cz.helmisek.evtademo.R;
import cz.helmisek.evtademo.databinding.ActivityMainBinding;
import cz.helmisek.evtademo.viewmodel.MainActivityViewModel;
import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;


public class MainActivity extends ViewModelActivity<ActivityMainBinding, MainActivityViewModel>
{

	@Override
	public ViewModelBindingConfig getViewModelBindingConfig()
	{
		return new ViewModelBindingConfig(R.layout.activity_main, MainActivityViewModel.class);
	}

}
