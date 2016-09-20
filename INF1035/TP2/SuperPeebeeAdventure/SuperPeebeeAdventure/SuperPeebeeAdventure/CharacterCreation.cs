using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SuperPeebeeAdventure
{
    
   
    public partial class CharacterCreation : Form
    {
        
        int choixPlayerAvatar;


        public CharacterCreation()
        {
            InitializeComponent();
            tboxName.MaxLength = 10;

            //event handler pour definir la classe présentement choisie
            radioBtnMonk.CheckedChanged += new EventHandler(ARadioButton_CheckedChanged);
            radioBtnOrc.CheckedChanged += new EventHandler(ARadioButton_CheckedChanged);
            radioBtnOverseer.CheckedChanged += new EventHandler(ARadioButton_CheckedChanged);
        }

       
            //event handler pour definir la classe présentement choisie
        protected void ARadioButton_CheckedChanged(object sender, EventArgs e)
        {
            if (sender is RadioButton)
            {
                RadioButton radioButton = (RadioButton)sender;
                if (radioButton.Checked && radioButton.Name == "radioBtnMonk")
                {
                    rtbInfoClasse.Text = "The MONK class starts with a Katar and 5 additional HP";
                }
                if (radioButton.Checked && radioButton.Name == "radioBtnOrc")
                {
                    rtbInfoClasse.Text = "The ORC class starts with a 2-H axe and 5 additional HP";
                }
                if (radioButton.Checked && radioButton.Name == "radioBtnOverseer")
                {
                    rtbInfoClasse.Text = "The OVERSEER class starts with a Legendary Sword, THE EYE OF EVIL, but 5 less HP";
                }
            }
        }

        //methode pour avoir le choix de l'avatar
        public int getChoixAvatar
        {
            get { return choixPlayerAvatar; }
        }

        public string getPlayerName
        {
            get { return tboxName.Text; }
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
            //choix avatar
            if (radioBtnOrc.Checked)
            {
                choixPlayerAvatar = 1;
            }
            else if (radioBtnOverseer.Checked)
            {
                choixPlayerAvatar = 2;
            }
            else if (radioBtnMonk.Checked)
            {
                choixPlayerAvatar = 3;
            }
            Close();
        }

    
    }
}
