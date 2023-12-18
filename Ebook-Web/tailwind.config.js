/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: ['./src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        sans: ['PingFang SC', 'system-ui', 'Noto Sans SC', ...defaultTheme.fontFamily.sans],
        note: ['PingFang SC', 'Noto Sans SC', 'sans-serif', ...defaultTheme.fontFamily.sans]
      }
    }
  },
  plugins: [require('@tailwindcss/forms'), require('daisyui'), require('@tailwindcss/typography')],
  darkMode: 'class',
  daisyui: {
    themes: [
      {
        light: {
          ...require('daisyui/src/theming/themes')['light'],
          primary: '#fb7185',
          'primary-content': '#FFF',
          neutral: '#fb7185',
          'neutral-content': '#FFF'
        },
        dark: {
          ...require('daisyui/src/theming/themes')['dark'],
          primary: '#fb7185'
        }
      },
      'cupcake',
      'bumblebee',
      'emerald',
      'corporate',
      'synthwave',
      'retro',
      'cyberpunk',
      'valentine',
      'halloween',
      'garden',
      'forest',
      'aqua',
      'lofi',
      'pastel',
      'fantasy',
      'wireframe',
      'black',
      'luxury',
      'dracula',
      'cmyk',
      'autumn',
      'business',
      'acid',
      'lemonade',
      'night',
      'coffee',
      'winter',
      'dim',
      'nord',
      'sunset'
    ],
    darkTheme: 'dark'
  }
}
