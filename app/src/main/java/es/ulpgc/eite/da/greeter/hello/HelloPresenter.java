package es.ulpgc.eite.da.greeter.hello;

import java.lang.ref.WeakReference;

public class HelloPresenter implements HelloContract.Presenter {

  public static String TAG = HelloPresenter.class.getSimpleName();

  private WeakReference<HelloContract.View> view;
  private HelloState state;
  private HelloContract.Model model;
  private HelloContract.Router router;

  public HelloPresenter(HelloState state) {
    this.state = state;
  }

  @Override
  public void onStart() {

    // call the model
    state.data = model.getData();

  }

  @Override
  public void onRestart() {

    // initialize the state if is necessary
    if (state.data == null) {

      // call the model
      state.data = model.getData();
    }

  }

  @Override
  public void onResume() {

    // use passed state if is necessary
    HelloState savedState = router.getDataFromPreviousScreen();
    if (savedState != null) {

      // update the state
      state.data = savedState.data;
    }

    // update the view
    view.get().displayData(state);
  }

  @Override
  public void onGreetButtonClicked(String data) {
    // Log.e(TAG, "onGreetButtonClicked()");

    // set view state
    state.data = data;

    // update the view
    view.get().displayData(state);
  }


  @Override
  public void injectView(WeakReference<HelloContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HelloContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(HelloContract.Router router) {
    this.router = router;
  }


}
