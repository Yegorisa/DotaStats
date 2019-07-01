package com.egoregorov.dotastats.navigation.home

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.egoregorov.dotastats.R
import com.egoregorov.dotastats.models.DotaHeroSimple
import com.egoregorov.dotastats.utils.*
import kotlinx.android.synthetic.main.home_character_view_holder.view.*

class HeroListAdapter : RecyclerView.Adapter<HeroListAdapter.HeroItem>() {

    var listOfHeroes: List<DotaHeroSimple> = mutableListOf()
        set(value) {
            field = value.sortedBy { it.localisedName }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_character_view_holder, parent, false)
        return HeroItem(view)
    }

    override fun getItemCount(): Int {
        return listOfHeroes.size
    }

    override fun onBindViewHolder(holder: HeroItem, position: Int) {
        val hero = listOfHeroes[position]
        holder.setHero(hero)
    }

    class HeroItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("CheckResult")
        fun setHero(hero: DotaHeroSimple) {
            itemView.homeCharacterViewHolderHeroName.text = hero.localisedName
            when (hero.primaryAttr) {
                AGILITY_ATTR -> {
                    itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_agility)
                }
                STRENGTH_ATTR -> {
                    itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_strength)
                }
                INTELLIGENCE_ATTR -> {
                    itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_intelligence)
                }
            }
                        GlideApp.with(itemView.context).load(OPEN_DOTA_URL + hero.iconUrl.drop(1))
            GlideApp
                .with(itemView.context)
                .load(OPEN_DOTA_URL + hero.iconUrl.drop(1))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.homeCharacterViewHolderHeroIcon)
        }
    }
}