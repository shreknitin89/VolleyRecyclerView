package app.nitin.com.volleyrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ContactViewHolder> {

    private List<Result> filmList;

    public FilmAdapter(List<Result> filmList) {
        this.filmList = filmList;
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Result result = filmList.get(i);
        contactViewHolder.vTrack.setText(result.getTrackName());
        contactViewHolder.vArtist.setText(result.getArtistName());
        contactViewHolder.vCountry.setText(result.getCountry());
        contactViewHolder.vTrackNo.setText(""+result.getTrackNumber());
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.music_list, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTrack;
        protected TextView vArtist;
        protected TextView vCountry;
        protected TextView vTrackNo;

        public ContactViewHolder(View v) {
            super(v);
            vTrack = (TextView) v.findViewById(R.id.titleName);
            vArtist = (TextView) v.findViewById(R.id.artistName);
            vCountry = (TextView) v.findViewById(R.id.country);
            vTrackNo = (TextView) v.findViewById(R.id.trackNo);
        }
    }
}