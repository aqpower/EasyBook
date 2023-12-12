/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme')

module.exports = {
  content: ['./src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Inter var', 'Noto Sans SC', ...defaultTheme.fontFamily.sans],
        note: ['Noto Sans SC', 'sans-serif', ...defaultTheme.fontFamily.sans]
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
          'primary': '#fb7185',
          'primary-content': '#FFF',
        },
        dark: {
          ...require('daisyui/src/theming/themes')['dark'],
          primary: '#fb7185'
        }
      }
    ],
    darkTheme: 'dark'
  }
}
