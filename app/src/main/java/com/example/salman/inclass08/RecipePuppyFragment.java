package com.example.salman.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipePuppyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipePuppyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipePuppyFragment extends Fragment implements RecipePuppyAdapter.IMusicAdapter{

    private OnFragmentInteractionListener mListener;
    ArrayList<String> recipeMenu;
    final static String BASE_URL = "http://www.recipepuppy.com/api/";

    RecipePuppyAdapter contactListAdapterRecyclerVIew;
    LinearLayoutManager layoutManager;
    int count = 0;
    ArrayList<Integer> countList;
    EditText edtDish;
    View view;

    public RecipePuppyFragment() {
        // Required empty public constructor
    }


    public static RecipePuppyFragment newInstance(String param1, String param2) {
        RecipePuppyFragment fragment = new RecipePuppyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        recipeMenu = new ArrayList<RecipeMenu>();
        recipeMenu = new ArrayList<>();
        view =  inflater.inflate(R.layout.fragment_recipe_puppy, container, false);
//
        final TextView ingredientPermenent = (TextView) view.findViewById(R.id.editTextMenuPermanent);
        edtDish = (EditText) view.findViewById(R.id.editTextDish);

//        RecipeMenu r = new RecipeMenu();
//        r.setName(ingredientPermenent.getText().toString());
//        r.setButton();

        final ImageButton img = (ImageButton) view.findViewById(R.id.imageButtonAddPermanent);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipeMenu.size()>4){
                    Toast.makeText(getContext(),"Maximum size reached",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!ingredientPermenent.getText().toString().trim().isEmpty()){
                        recipeMenu.add(ingredientPermenent.getText().toString().trim());
                        ingredientPermenent.setText("");
                        setAdapterAndNotify();
                    }
                    else{
                        Toast.makeText(getContext(),"Please enter the ingredient",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        view.findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtDish.getText().toString().trim().equals("") ||
                        edtDish.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Please enter the Dish",Toast.LENGTH_LONG).show();

                }
                else{
                    RequestParams req = new RequestParams("GET", BASE_URL);
                    String ing = TextUtils.join(",", recipeMenu);
                    req.addParam("format", "xml");
                    req.addParam("i", ing);
                    req.addParam("q", edtDish.getText().toString().trim());
                    mListener.setUpRequestParam(req);

                }

            }
        });


        // Inflate the layout for this fragment
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void setAdapterAndNotify(){
        contactListAdapterRecyclerVIew = new RecipePuppyAdapter(recipeMenu, getContext(), new RecipePuppyFragment());
//        public ContactListAdapterRecyclerVIew(ArrayList<Contact> contacts, Context context, int resource,IMusicAdapter iMusicAdapter ) {

        RecyclerView contactsList = ((RecyclerView) getView().findViewById(R.id.recyelerViewMenu));
        contactsList.setAdapter(contactListAdapterRecyclerVIew);
        layoutManager = new LinearLayoutManager(getActivity());
        contactsList.setLayoutManager(layoutManager);
        contactListAdapterRecyclerVIew.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void goToDetailsFragment(int position) {

    }

    @Override
    public void addRow() {
        if(count!=5){

            count++;
        }
//        setAdapterAndNotify(view);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void setUpRequestParam(RequestParams requestParam);
    }
}
