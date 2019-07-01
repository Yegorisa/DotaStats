package com.egoregorov.dotastats.navigation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egoregorov.dotastats.R
import com.egoregorov.dotastats.models.DotaHeroSimple
import com.egoregorov.dotastats.utils.AGILITY_ATTR
import com.egoregorov.dotastats.utils.INTELLIGENCE_ATTR
import com.egoregorov.dotastats.utils.STRENGTH_ATTR
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
        fun setHero(hero: DotaHeroSimple) {
            itemView.homeCharacterViewHolderHeroName.text = hero.localisedName
            when(hero.primaryAttr){
                AGILITY_ATTR ->{itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_agility)}
                STRENGTH_ATTR ->{itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_strength)}
                INTELLIGENCE_ATTR ->{itemView.homeCharacterViewHolderHeroAttrIcon.setImageResource(R.drawable.ic_intelligence)}
            }
        }
    }
}