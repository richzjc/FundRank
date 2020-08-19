package com.richzjc.fundrank;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

public class FundRankItemEntity implements Parcelable {

    public String code;
    public String name;
    public OtherBean other;
    public MonetaryBean monetary;
    public double founded_size;
    public long end_date;
    public String rank;

    private SpannableStringBuilder[] rightScrollBuilders;

    public static class MonetaryBean implements Parcelable {
        public Double weekly;
        public Double daily;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.weekly);
            dest.writeValue(this.daily);
        }

        public MonetaryBean() {
        }

        protected MonetaryBean(Parcel in) {
            this.weekly = (Double) in.readValue(Double.class.getClassLoader());
            this.daily = (Double) in.readValue(Double.class.getClassLoader());
        }

        public static final Creator<MonetaryBean> CREATOR = new Creator<MonetaryBean>() {
            @Override
            public MonetaryBean createFromParcel(Parcel source) {
                return new MonetaryBean(source);
            }

            @Override
            public MonetaryBean[] newArray(int size) {
                return new MonetaryBean[size];
            }
        };
    }

    public static class OtherBean implements Parcelable {

        public Double one_week;
        public Double one_month;
        public Double three_month;
        public Double six_month;
        public Double one_year;
        public Double two_year;
        public Double three_year;
        public Double five_year;
        public Double ten_year;
        public Double since_year;
        public Double since_founded;
        public Double daily;
        public Double net_value;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.one_week);
            dest.writeDouble(this.one_month);
            dest.writeDouble(this.three_month);
            dest.writeDouble(this.six_month);
            dest.writeDouble(this.one_year);
            dest.writeDouble(this.two_year);
            dest.writeDouble(this.three_year);
            dest.writeDouble(this.five_year);
            dest.writeDouble(this.ten_year);
            dest.writeDouble(this.since_year);
            dest.writeDouble(this.since_founded);
            dest.writeDouble(this.daily);
            dest.writeDouble(this.net_value);
        }

        public OtherBean() {

        }

        protected OtherBean(Parcel in) {
            this.one_week = in.readDouble();
            this.one_month = in.readDouble();
            this.three_month = in.readDouble();
            this.six_month = in.readDouble();
            this.one_year = in.readDouble();
            this.two_year = in.readDouble();
            this.three_year = in.readDouble();
            this.five_year = in.readDouble();
            this.ten_year = in.readDouble();
            this.since_year = in.readDouble();
            this.since_founded = in.readDouble();
            this.daily = in.readDouble();
            this.net_value = in.readDouble();
        }

        public static final Parcelable.Creator<OtherBean> CREATOR = new Parcelable.Creator<OtherBean>() {
            @Override
            public OtherBean createFromParcel(Parcel source) {
                return new OtherBean(source);
            }

            @Override
            public OtherBean[] newArray(int size) {
                return new OtherBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeParcelable(this.other, flags);
        dest.writeParcelable(this.monetary, flags);
        dest.writeDouble(this.founded_size);
        dest.writeLong(this.end_date);
        dest.writeString(this.rank);
    }

    public FundRankItemEntity() {
    }

    protected FundRankItemEntity(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.other = in.readParcelable(OtherBean.class.getClassLoader());
        this.monetary = in.readParcelable(MonetaryBean.class.getClassLoader());
        this.founded_size = in.readDouble();
        this.end_date = in.readLong();
        this.rank = in.readString();
    }

    public static final Parcelable.Creator<FundRankItemEntity> CREATOR = new Parcelable.Creator<FundRankItemEntity>() {
        @Override
        public FundRankItemEntity createFromParcel(Parcel source) {
            return new FundRankItemEntity(source);
        }

        @Override
        public FundRankItemEntity[] newArray(int size) {
            return new FundRankItemEntity[size];
        }
    };

    public SpannableStringBuilder getLeftTitle() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(name).append("\n");
        int start = builder.length();
        builder.append(code);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, start, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(14, true), 0, start, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), start, builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan(11, true), start, builder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return builder;
    }

    public SpannableStringBuilder[] getRightScrollBuilder() {
        if (rightScrollBuilders == null) {
            rightScrollBuilders = new SpannableStringBuilder[9];

            SpannableStringBuilder zero = new SpannableStringBuilder();
            zero.append(String.valueOf(other.net_value)).append("\n");
            int start = zero.length();
            zero.append(String.valueOf(end_date));
            rightScrollBuilders[0] = zero;


            SpannableStringBuilder first = new SpannableStringBuilder();
            first.append(String.valueOf(other.daily));
            rightScrollBuilders[1] = first;


            SpannableStringBuilder second = new SpannableStringBuilder();
            second.append(String.valueOf(other.one_month));
            rightScrollBuilders[2] = second;


            SpannableStringBuilder third = new SpannableStringBuilder();
            third.append(String.valueOf(other.three_month));
            rightScrollBuilders[3] = third;


            SpannableStringBuilder fourth = new SpannableStringBuilder();
            fourth.append(String.valueOf(other.one_year));
            rightScrollBuilders[4] = fourth;


            SpannableStringBuilder fifth = new SpannableStringBuilder();
            fifth.append(String.valueOf(other.three_year));
            rightScrollBuilders[5] = fifth;


            SpannableStringBuilder sixth = new SpannableStringBuilder();
            sixth.append(String.valueOf(other.since_founded));
            rightScrollBuilders[6] = sixth;


            SpannableStringBuilder seventh = new SpannableStringBuilder();
            seventh.append(String.valueOf(founded_size));
            rightScrollBuilders[7] = seventh;

            SpannableStringBuilder eight = new SpannableStringBuilder();
            eight.append(String.valueOf(rank));
            rightScrollBuilders[8] = eight;
        }

        return rightScrollBuilders;
    }
}
